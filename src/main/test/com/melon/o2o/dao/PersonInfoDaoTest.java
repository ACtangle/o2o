package com.melon.o2o.dao;

import com.melon.o2o.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class PersonInfoDaoTest extends BaseTest {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    public void queryPersonInfoById() {
        PersonInfo personInfo = personInfoDao.queryPersonInfoById(2);
        System.out.println(personInfo);
    }

    @Test
    public void insertPersonInfo() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("我爱你");
        personInfo.setGender("女");
        personInfo.setUserType(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        int result = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1,result);
    }

}