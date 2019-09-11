package com.melon.o2o.dao;

import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.entity.WechatAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class WechatAuthDaoTest extends BaseTest{

    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    public void queryWechatInfoByOpenId() {
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("1");
        System.out.println(wechatAuth);
    }

    @Test
    public void insertWechatAuth() {
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = personInfoDao.queryPersonInfoById(2);
        wechatAuth.setCreateTime(new Date());
        wechatAuth.setOpenId("1");
        wechatAuth.setPersonInfo(personInfo);
        int result = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1,result);
    }
}