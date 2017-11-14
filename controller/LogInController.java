package com.zhizoo.controller;

import com.zhizoo.model.User;
import com.zhizoo.service.UserService;
import com.zhizoo.util.WechatUtils;
import org.apache.log4j.Logger;
import org.jboss.logging.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;


/**
 * Created by temp on 2016/7/21.
 */
@Controller
public class LogInController {
    private static Logger LOG = Logger.getLogger(LogInController.class);

    @Resource
    UserService userService;

    @RequestMapping("/test")
    private String localLogin(@RequestParam("tableId") String tableId, RedirectAttributes attr) {
        if (tableId!="") {
            attr.addAttribute("tableId",tableId);
//            session.setAttribute("tableId",tableId);
        }
        String openid = "test";
        String nickname = "测试号";
        String headimgurl = "";
        String tableCode = "01A1";

        attr.addAttribute("openid",openid);
        try {
            attr.addAttribute("nickname",new String(nickname.getBytes("utf-8"),"ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        attr.addAttribute("headimgurl",headimgurl);
        attr.addAttribute("tableCode",tableCode);
//        session.setAttribute("openid",openid);
//        session.setAttribute("nickname",nickname);
//        session.setAttribute("headimgurl",headimgurl);
//        session.setAttribute("tableCode",tableCode);
        return "redirect:/login";
    }


    /**
     * 授权后登录：
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String verifyLogin(HttpServletRequest request, HttpSession session) {
        String openid = request.getParameter("openid");
        String nickname = request.getParameter("nickname");
        String headimgurl = request.getParameter("headimgurl");

        User user = userService.findByOpenId(openid);
        if (null==user) {
            user = new User();
            user.setOpenId(openid);
            user.setNickname(nickname);
            user.setHeadimgurl(headimgurl);
            userService.saveOrUpdateUser(user);
        }
        session.setAttribute("openId",openid);
        String tableCode = request.getParameter("tableCode");
        return "redirect:/"+tableCode;
    }

    /**
     * 登出后返回登录页面
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        LOG.info(session.getAttribute("openId")+"log out");
        session.removeAttribute("openId");
        return "login";
    }


    /**
     * 扫码：
     * 扫码得到tableCode并存入session，跳转微信授权；若已授权，跳转首页
     * @param session
     * @param tableCode
     * @return
     */
    @RequestMapping(value = "/{tableCode}", method = RequestMethod.GET)
    public String showIndex(HttpSession session,
                            @PathVariable("tableCode") String tableCode) {
        session.setAttribute("tableCode",tableCode);
        if (null==session.getAttribute("openId")) {
            return "redirect:/wechat/code";
        }
        String openId = session.getAttribute("openId").toString();
        String userId = String.valueOf(userService.findByOpenId(openId).getId());
        session.setAttribute("userId",userId);
        return "index";
    }

}
