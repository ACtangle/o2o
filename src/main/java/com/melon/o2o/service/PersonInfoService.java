package com.melon.o2o.service;

import com.melon.o2o.entity.PersonInfo;

/**
 * @ClassName PersonInfoService
 * @Description
 * @Author melon
 * @Date 2019-09-11 10:54
 * @Version
 */

public interface PersonInfoService {

    PersonInfo getPersonInfoById(Long userId);

}
