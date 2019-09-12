package com.melon.o2o.service;

import com.melon.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @ClassName ShopCategoryService
 * @Description
 * @Author melon
 * @Date 2019-08-19 23:12
 * @Version
 */

public interface ShopCategoryService {

    public static final String SCLISTKEY = "shopcategorylist";
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

}
