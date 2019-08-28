package com.melon.o2o.service.imp;

import com.melon.o2o.dao.HeadLineDao;
import com.melon.o2o.entity.HeadLine;
import com.melon.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName HeadLineServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-29 02:43
 * @Version
 */

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
