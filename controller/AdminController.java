package com.zhizoo.controller;

import com.zhizoo.model.Dish;
import com.zhizoo.service.DishService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by temp on 2016/8/3.
 */
@Controller
public class AdminController {
    private static Logger LOG = Logger.getLogger(AdminController.class);

    @Resource
    private DishService dishService;

    @RequestMapping(value = "/addDish.do",method = RequestMethod.POST)
    public void addDish(Dish dish) {
        dishService.saveOrUpdateDish(dish);
        LOG.info("create a dish: " + dish.toString());
    }

    @RequestMapping(value = "/showDish.do",method = RequestMethod.GET)
    public Dish showDish(@RequestParam("dishId") String dishId) {
        Dish dish = dishService.findByDishId(dishId);
        LOG.info("show dish by id: "+dish.toString());
        return dish;
    }

}
