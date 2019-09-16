package com.melon.o2o.interceptor.shopadmin;

import com.melon.o2o.entity.PersonInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName ShopLoginInterceptor
 * @Description 店铺管理系统登录拦截器
 * @Author melon
 * @Date 2019-09-16 12:14
 * @Version
 */

public class ShopLoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Object userObj = httpServletRequest.getSession().getAttribute("user");
        if (userObj != null) {
            PersonInfo user = (PersonInfo) userObj;
            if (user != null && user.getUserId() != null && user.getEnableStatus() == 1) {
                return true;
            }
        }

        //若不满足验证界面
        PrintWriter out = httpServletResponse.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('" + httpServletRequest.getContextPath() + "/local/login?usertype=2','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
