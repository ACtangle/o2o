package com.melon.o2o.dao;

import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import java.util.Date;

import static org.junit.Assert.*;

public class LocalAuthDaoTest extends BaseTest{

    @Autowired
    private LocalAuthDao localAuthDao;

    @Test
    public void queryLocalByUserNameAndPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd("melon","melon");
        System.out.println(localAuth);
    }

    @Test
    public void queryLocalByUserId() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        System.out.println(localAuth);
    }

    @Test
    public void insertLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        localAuth.setUsername("melon");
        localAuth.setPassword("melon");
        localAuth.setCreateTime(new Date());
        localAuthDao.insertLocalAuth(localAuth);
    }

    @Test
    public void updateLocalAuth() {
        int result = localAuthDao.updateLocalAuth(1L,"melon","root","melon",new Date());
        assertEquals(1,result);
    }
}