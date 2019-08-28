package com.melon.o2o.service;

import com.melon.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName HeadLineService
 * @Description
 * @Author melon
 * @Date 2019-08-29 02:42
 * @Version
 */

public interface HeadLineService {

    /**
     * 根据传入的条件返回指定的头条列表
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
