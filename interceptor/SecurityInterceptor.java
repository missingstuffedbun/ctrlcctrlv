package com.zhizoo.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by temp on 2016/7/28.
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = Logger.getLogger(SecurityInterceptor.class);

    /**
     * 未登录则转登陆
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("IN PREHANDLE");
        String loginUrl = request.getContextPath()+"/login";
        if (null==request.getSession().getAttribute("loggedInUser")) {
            response.sendRedirect(loginUrl);
            return false;
        }
        return true;
    }

    /**
     * 先放着，随便删
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 先放着，随便删
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
