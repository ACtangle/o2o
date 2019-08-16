package com.melon.o2o.dao;

import com.melon.o2o.entity.Area;

import java.util.List;

/**
 * @ClassName AreaDao
 * @Description
 * @Author melon
 * @Date 2019-08-16 18:44
 * @Version
 */

public interface AreaDao {

    /**
     * 列出区域列表
     * @return  areaList
     */
    List<Area> queryArea();

}
