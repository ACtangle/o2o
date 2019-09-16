package com.melon.o2o.interceptor.shopadmin;

import com.melon.o2o.entity.Shop;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName ShopLoginInterceptor
 * @Description 店家管理系统拦截器
 * @Author melon
 * @Date 2019-09-16 12:14
 * @Version
 */

public class ShopPermissionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Shop currentShop = (Shop)httpServletRequest.getSession().getAttribute("currentShop");
        @SuppressWarnings("unchecked")
        List<Shop> shopList = (List<Shop>)httpServletRequest.getSession().getAttribute("shopList");
        if (currentShop!=null && shopList !=null) {
            for (Shop shop: shopList){
                if (shop.getShopId().equals(currentShop.getShopId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
