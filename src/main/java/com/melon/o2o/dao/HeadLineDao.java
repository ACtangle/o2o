package com.melon.o2o.dao;

import com.melon.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName HeadLineDao
 * @Description
 * @Author melon
 * @Date 2019-08-29 02:22
 * @Version
 */

public interface HeadLineDao {


    List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLineCondition);

    HeadLine queryHeadLineById(long lineId);
}
