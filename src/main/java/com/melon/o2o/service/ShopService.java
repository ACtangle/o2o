package com.melon.o2o.service;

import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName ShopServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:15
 * @Version
 */


public interface ShopService {
    /**
     * 根据shopCondition返回分页店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex, int pageSize);

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)throws ShopOperationException;

    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}

