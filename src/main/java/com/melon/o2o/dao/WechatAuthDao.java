package com.melon.o2o.dao;

import com.melon.o2o.entity.WechatAuth;

/**
 * @ClassName WechatAuthDao
 * @Description
 * @Author melon
 * @Date 2019-09-11 10:01
 * @Version
 */

public interface WechatAuthDao {

    WechatAuth queryWechatInfoByOpenId(String openId);

    int insertWechatAuth(WechatAuth wechatAuth);
}
