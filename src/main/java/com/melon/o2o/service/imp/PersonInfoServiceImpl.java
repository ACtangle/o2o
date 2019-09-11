package com.melon.o2o.service.imp;

import com.melon.o2o.dao.PersonInfoDao;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PersonInfoServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-09-11 10:55
 * @Version
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }


}
