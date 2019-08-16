package com.melon.o2o.service.imp;

import com.melon.o2o.dao.AreaDao;
import com.melon.o2o.entity.Area;
import com.melon.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AreaServiceImp
 * @Description
 * @Author melon
 * @Date 2019-08-16 19:32
 * @Version
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
