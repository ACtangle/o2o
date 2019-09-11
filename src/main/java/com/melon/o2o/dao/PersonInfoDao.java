package com.melon.o2o.dao;

import com.melon.o2o.entity.PersonInfo;

/**
 * @ClassName PersonInfoDao
 * @Description
 * @Author melon
 * @Date 2019-09-11 09:59
 * @Version
 */

public interface PersonInfoDao {

    /**
     * 根据账号id查找账号
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     * 添加用户信息
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);
}
