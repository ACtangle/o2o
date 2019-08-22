package com.melon.o2o.service;

import com.melon.o2o.dao.BaseTest;
import com.melon.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaList() {
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);
        assertEquals("新港中",areaList.get(0).getAreaName());
    }
}