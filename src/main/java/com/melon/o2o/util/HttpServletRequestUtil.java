package com.melon.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HttpServletRequestUtil
 * @Description
 * @Author melon
 * @Date 2019-08-19 16:37
 * @Version
 */


public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request , String key) {
        try {
            return Long.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static double getDouble(HttpServletRequest request , String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try{
            return Boolean.getBoolean(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request ,String key){
        try{
            String result = request.getParameter(key);
            if (null != request){
                result = result.trim();
            }
            if ("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e) {
            return null;
        }
    }
}
