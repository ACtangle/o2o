package com.melon.o2o.service;

import com.melon.o2o.dto.WechatAuthExecution;
import com.melon.o2o.entity.WechatAuth;
import com.melon.o2o.exceptions.WechatAuthOperationException;

/**
 * @ClassName PersonInfoService
 * @Description
 * @Author melon
 * @Date 2019-09-11 10:54
 * @Version
 */

public interface WechatAuthService {


    /**
     * 通过openId查找品平台对应的微信账号
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * 注册本平台的微信账号
     * @param wechatAuth
     * @return
     * @throws WechatAuthOperationException
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;

}
