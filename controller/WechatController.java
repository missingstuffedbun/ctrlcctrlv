package com.zhizoo.controller;

import com.zhizoo.service.UserService;
import com.zhizoo.util.SignUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * Created by temp on 2016/8/3.
 */
@Controller
public class WechatController {

    private static Logger LOG = Logger.getLogger(WechatController.class);

    /**
     * 跳转WechatTokenServlet：code换取token和openid
     * @param request
     * @param attr
     * @return
     */
    @RequestMapping(value = "/wechat/getCode", method = RequestMethod.GET)
    public String getCode(HttpServletRequest request, RedirectAttributes attr) {
        String code = request.getParameter("code");
        attr.addAttribute("code",code);
        return "redirect:/token";
    }

    /**
     * 跳转WechatInfoServlet：获取openid的用户信息
     * @param request
     * @param attr
     * @return
     */
    @RequestMapping(value = "/wechat/getToken", method = RequestMethod.GET)
    public String getToken(HttpServletRequest request, RedirectAttributes attr) {
        String access_token = request.getParameter("access_token");
        attr.addAttribute("access_token",access_token);
        String openid = request.getParameter("openid");
        attr.addAttribute("openid",openid);
        return "redirect:/info";
    }

    /**
     * 跳转LoginController：登陆
     * @param request
     * @param attr
     * @return
     */
    @RequestMapping(value = "/wechat/getInfo", method = RequestMethod.GET)
    public String getInfo(HttpServletRequest request, RedirectAttributes attr) {
        String openid = request.getParameter("openid");
        String nickname = request.getParameter("nickname");
        String headimgurl = request.getParameter("headimgurl");
        attr.addAttribute("openid",openid);
        try {
            attr.addAttribute("nickname",new String(nickname.getBytes("utf-8"),"ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        attr.addAttribute("headimgurl",headimgurl);
        return "redirect:/login";
    }



    /**
     * 微信加密签名方法：
     * signature结合了开发者填写的token和请求中的timestamp、nonce
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtils.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (Exception e) {
            LOG.info(e.toString());
        } finally {
            out.close();
            out = null;
        }
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        //先留着，万一以后想用了呢，至少做个自动回复什么的
    }

}
