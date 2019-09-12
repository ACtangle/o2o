package com.melon.o2o.service;

import com.melon.o2o.dao.BaseTest;
import com.melon.o2o.dto.LocalAuthExecution;
import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import static org.junit.Assert.*;

public class LocalAuthServiceTest extends BaseTest {

    @Autowired
    private LocalAuthService localAuthService;


    @Test
    public void getLocalAuthByUsernameAndPwd() {
    }

    @Test
    public void getLocalAuthByUserId() {
    }

    @Test
    public void bindLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "root";
        String password= "root";
        personInfo.setUserId(3L);
        localAuth.setPersonInfo(personInfo);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
        System.out.println(localAuthExecution);
    }

    @Test
    public void modifyLocalAuth() {
        long userId =3L;
        String username = "root";
        String password= "root";
        String newPssword= "melon";
        LocalAuth localAuth = localAuthService.getLocalAuthByUserId(3L);
        System.out.println(localAuth.getPassword()); //66bqy5sb2llq55952q6l62qs59250529
        LocalAuthExecution localAuthExecution = localAuthService.modifyLocalAuth(userId,username,password,newPssword);
        System.out.println(localAuthExecution);
        LocalAuth temp = localAuthService.getLocalAuthByUserId(3L);
        System.out.println(temp.getPassword());
    }
}