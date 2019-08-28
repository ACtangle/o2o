package com.melon.o2o.dao;

import com.melon.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HeadLineDaoTest extends BaseTest{

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void queryHeadLine() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        System.out.println(headLineList.size());
    }

    @Test
    public void queryHeadLineById() {
    }
}