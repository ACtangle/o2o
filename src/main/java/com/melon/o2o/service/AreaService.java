package com.melon.o2o.service;

import com.melon.o2o.entity.Area;

import java.util.List;

/**
 * @ClassName AreaService
 * @Description
 * @Author melon
 * @Date 2019-08-16 19:32
 * @Version
 */

public interface AreaService {
    public static  final String AREALISTKEY = "arealist";
    List<Area> getAreaList();
}
