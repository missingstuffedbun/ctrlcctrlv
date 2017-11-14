package com.zhizoo.controller;

import com.zhizoo.model.*;
import com.zhizoo.service.*;
import com.zhizoo.util.JSONUtils;
import com.zhizoo.vo.CartViewObject;
import com.zhizoo.vo.CategoryViewObject;
import com.zhizoo.vo.HistoryViewObject;
import com.zhizoo.vo.TableViewObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by temp on 2016/7/18.
 */
@Controller
public class MainController {
    private static Logger LOG = Logger.getLogger(MainController.class);

    @Resource
    UserService userService;
    @Resource
    OrderService orderService;
    @Resource
    TableLogService tableLogService;
    @Resource
    DishService dishService;
    @Resource
    RestaurantService restaurantService;
    @Resource
    OrderLogService orderLogService;
    @Resource
    UserRelationService relationService;
    @Resource
    UserTableService userTableService;

    /**
     * 开桌方法：
     * 1. 点击开桌，创建tableLog
     * 2. 输入validCode，获取对应tableLog
     * @param validCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/date.do",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public TableViewObject getTable(HttpSession session,
                                    @RequestParam("validCode") String validCode) {
        String tableCode = session.getAttribute("tableCode").toString();
        String userId = session.getAttribute("userId").toString();
        User user = userService.findByUserId(userId);

        Order order = null;
        TableLog tableLog = null;

        if (null == session.getAttribute("tableId")) {
            tableLog = tableLogService.recentTable(tableCode);
        } else {
            tableLog = tableLogService.findTableById(session.getAttribute("tableId").toString());
        }

//        validCode为空时，为该用户创建桌
        if (validCode.length()==0 || null == tableLog) {
            tableLog = tableLogService.createTable(userId,tableCode);
            order = orderService.initOrder(tableLog);
            LOG.info("user "+userId+" init table "+tableCode);
            userTableService.createUserTable(userId, String.valueOf(tableLog.getId()));
        }

//        validCode与tableCode最近一桌桌号相同时，加入桌
        if (tableCode.equals(tableLog.getTableCode()) && validCode.equals(tableLog.getValidCode())) {
            tableLogService.addFriends(tableLog, userId);
            LOG.info("user "+userId+" join table "+tableCode);
            order = orderService.findByOrderId(String.valueOf(tableLog.getId()));
        }

        Restaurant restaurant = getRestaurant(tableLog);
        Set<String> categorySet = dishService.getRCategory(dishService.getDishesByRestaurant(restaurant));

        if (null==session.getAttribute("tableId")) {
            session.setAttribute("tableId",tableLog.getId());
        }

        TableViewObject tableViewObject = new TableViewObject(user,tableLog,restaurant,categorySet);
        return tableViewObject;
    }


    /**
     * 查询餐厅信息：
     * 通过桌记录的桌号，查询餐厅
     * @param tableLog
     * @return
     */
    protected Restaurant getRestaurant(TableLog tableLog) {
        String restaurantId = tableLog.getTableCode().substring(0,2);
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        if (null!=restaurant) {
            LOG.info("get restaurant: "+restaurant.toString());
            return restaurant;
        }
        LOG.warn("get restaurant failed");
        return null;
    }

    /**
     * 获取分类下菜单方法：
     * category为空时返回restaurantId餐厅所有菜品
     * @param restaurantId
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/category.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public CategoryViewObject getMenu(@RequestParam("restaurantId") String restaurantId, @RequestParam("category") String category) {
//        获取餐厅的菜单
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        List<Dish> menu = dishService.getDishesByRestaurant(restaurant);
//        获取分类的菜单
        CategoryViewObject categoryViewObject = new CategoryViewObject(menu,category);
        return categoryViewObject;
    }

    /**
     * 点菜方法：
     * 创建OrderLog，更新Order
     * @param userId
     * @param tableId
     * @param dishId
     * @return
     */
    @ResponseBody
    @RequestMapping("/add.do")
    public boolean addToCart(@RequestParam("userId") String userId, @RequestParam("tableId") String tableId, @RequestParam("dishId") String dishId) {
        User user = userService.findByUserId(userId);

        Order order = orderService.findByOrderId(tableId);
        if (null == order || null == dishService.findByDishId(dishId)) {
            LOG.warn("add dish failed");
            return false;
        }
        orderLogService.createOrderLog(String.valueOf(user.getId()),dishId,String.valueOf(order.getId()));
        return true;
    }

    /**
     * 查看购物车方法：
     * @param userId
     * @param tableId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cart.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public CartViewObject getCart(@RequestParam("userId") String userId, @RequestParam("tableId") String tableId) {
        User user = userService.findByUserId(userId);

        Order order = orderService.findByOrderId(tableId);
        TableLog table = tableLogService.findTableById(tableId);

        Map<Integer,String> orderInfo = dishService.getOrderInfo(order.getOrderInfo());
        CartViewObject cartVO = new CartViewObject(order,user,table,orderInfo);
        return cartVO;
    }

    /**
     * 修改购物车方法：
     * @param userId
     * @param tableId
     * @param dishId
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public CartViewObject updateCart(@RequestParam("userId") String userId, @RequestParam("tableId") String tableId, @RequestParam("dishId") String dishId, @RequestParam("count") String count) {
        User user = userService.findByUserId(userId);

        TableLog table = tableLogService.findTableById(tableId);
        // 只有桌主有权限
        if (table.getUserId().equals(Long.parseLong(userId))) {
            updateOneDish(orderService.findByOrderId(tableId),dishId,count);
        }
        Order order = orderService.findByOrderId(tableId);
        Map<Integer,String> orderInfo = dishService.getOrderInfo(order.getOrderInfo());
        CartViewObject cartVO = new CartViewObject(order,user,table,orderInfo);
        return cartVO;
    }

    private void updateOneDish(Order order, String dishId, String count) {
        Map<String,Integer> dishes = new HashMap<String, Integer>();
        String updated;
        try {
            dishes = (Map<String,Integer> ) JSONUtils.unmarshal(order.getOrderInfo());
            for (Map.Entry<String,Integer> entry : dishes.entrySet()) {
                if (entry.getKey().equals(dishId)) {
                    entry.setValue(Integer.parseInt(count));
                    updated = JSONUtils.marshal(dishes);
                    order.setOrderInfo(updated);
                    orderService.saveOrUpdateOrder(order);
                }
            }
        } catch (IOException e) {
            LOG.warn(e.toString());
        }
    }


    /**
     * 下单方法：
     * 判断用户是否为开桌人，下单后更新好友关系、总价
     * @param userId
     * @param tableId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirm.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public CartViewObject getConfirmation(@RequestParam("userId") String userId, @RequestParam("tableId") String tableId) {
        User user = userService.findByUserId(userId);
        Order order = orderService.findByOrderId(tableId);
        TableLog table = tableLogService.findTableById(tableId);

        if (userId.equals(table.getUserId().toString())) {
            order.setFriends(table.getFriends());
            relationService.updateByTable(order);
        } else {
            LOG.warn("Access is denied");
        }

        Map<Integer,String> orderInfo = dishService.getOrderInfo(order.getOrderInfo());
        CartViewObject cartVO = new CartViewObject(order,user,table,orderInfo);
        order.setPrice(cartVO.getoPrice());
        orderService.saveOrUpdateOrder(order);
        return cartVO;
    }

    /**
     * 菜品详情方法：
     * @param dishId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dish.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Dish getDish(@RequestParam("dishId") String dishId) {
        return dishService.findByDishId(dishId);
    }

    /**
     * 查询历史记录方法：
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/history.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public HistoryViewObject getHistory(@RequestParam("userId") String userId) {
        Map<UserTable,Order> uto = userTableService.findHistory(userId);
        HistoryViewObject historyVO = new HistoryViewObject(uto,dishService.findAllName());
        return historyVO;
    }
}
