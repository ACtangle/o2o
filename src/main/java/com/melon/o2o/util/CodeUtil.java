package com.melon.o2o.util;

import com.google.code.kaptcha.Constants;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName CodeUtil
 * @Description
 * @Author melon
 * @Date 2019-08-20 14:15
 * @Version
 */

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取图片内kaptcha的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取表单内传递过来的验证码
        String verifyCodeActual = HttpServletRequestUtil.getStringFromMapByIndex(request,"verifyCodeActual",0);
        //比较
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
