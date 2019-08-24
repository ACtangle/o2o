package com.melon.o2o.dao;

import com.melon.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @ClassName ProductCategoryDao
 * @Description
 * @Author melon
 * @Date 2019-08-25 02:06
 * @Version
 */

public interface ProductCategoryDao {

    /**
     * 通过shopId 查询店铺商品类别
     * @param shopId
     * @return List<ProductCategory>
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

}