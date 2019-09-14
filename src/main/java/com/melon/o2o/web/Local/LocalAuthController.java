package com.melon.o2o.web.Local;

import com.melon.o2o.dto.LocalAuthExecution;
import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.enums.LocalAuthEnum;
import com.melon.o2o.exceptions.LocalAuthOperationException;
import com.melon.o2o.service.LocalAuthService;
import com.melon.o2o.util.CodeUtil;
import com.melon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LocalAuthController
 * @Description
 * @Author melon
 * @Date 2019-09-12 16:37
 * @Version
 */
@Controller
@RequestMapping(value = "/local", method = {RequestMethod.GET, RequestMethod.POST})
public class LocalAuthController {

    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/binglocalauth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindLocalAuth(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMes", "输入了错误的验证码");
            return modelMap;
        }

        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");

        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (userName != null && password != null && user != null && user.getUserId() != null) {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUsername(userName);
            localAuth.setPassword(password);
            localAuth.setPersonInfo(user);
            LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
            if (localAuthExecution.getState() == LocalAuthEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMes", localAuthExecution.getStateInfo());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMes", "用户名和密码不能为空");
        }
        return modelMap;
    }

    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMes", "输入了错误的验证码");
            return modelMap;
        }

        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (userName != null && password != null && user.getUserId() != null
                && newPassword != null && !password.equals(newPassword)) {
            try {
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if (localAuth == null || !localAuth.getUsername().equals(userName)) {
                    //判断有无该账号，如果有判断账号是不是相同
                    modelMap.put("success", false);
                    modelMap.put("errMes", "输入的账号非本次登录的账号");
                }
                LocalAuthExecution localAuthExecution = localAuthService.modifyLocalAuth(user.getUserId(),userName,password,newPassword);
                if (localAuthExecution.getState() == LocalAuthEnum.SUCCESS.getState()){
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMes", localAuthExecution.getStateInfo());
                    return modelMap;
                }
            } catch (LocalAuthOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMes", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMes", "请输入密码");
        }
        return modelMap;
    }

    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginCheck(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        boolean needVerify = HttpServletRequestUtil.getBoolean(request,"needVerify");

        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMes", "输入了错误的验证码");
            return modelMap;
        }

        String userName = HttpServletRequestUtil.getString(request, "userName");
        String password = HttpServletRequestUtil.getString(request, "password");

        if (userName != null && password !=null) {
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName,password);
            if (localAuth!=null) {
                modelMap.put("success",true);
                request.getSession().setAttribute("user",localAuth.getPersonInfo());
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","用户名或密码错误");
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMes", "请输入密码");
        }
        return modelMap;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> logout(HttpServletRequest request) {
        Map<String,Object> modelMap = new HashMap<>();
        request.getSession().setAttribute("user",null);
        modelMap.put("success",true);
        return modelMap;
    }
}
