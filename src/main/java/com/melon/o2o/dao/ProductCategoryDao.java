package com.melon.o2o.dao;

import com.melon.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 批量增加商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 根据商品分类id和店铺id删除指定商品分类
     * @param productCategoryId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}