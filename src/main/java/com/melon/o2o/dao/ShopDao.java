package com.melon.o2o.dao;

import com.melon.o2o.entity.Shop;

/**
 * @ClassName ShopDao
 * @Description
 * @Author melon
 * @Date 2019-08-17 17:19
 * @Version
 */

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return int
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return int
     */
    int updateShop(Shop shop);
}
