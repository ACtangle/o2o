package com.melon.o2o.dao;

import com.melon.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Description
 * @Author melon
 * @Date 2019-08-26 22:54
 * @Version
 */

public interface ProductDao {

    /**
     * 商品列表分页，可输入的条件有:商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition")Product productCondition,
                                   @Param("rowIndex") int rowIndex,
                                   @Param("pageSize")int pageSize);

    /**
     * 获取数量
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 根据productId查询指定的单个商品
     * @param productId
     * @return
     */
    Product queryProductByProductId(long productId);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 更新指定的某个商品
     * @param product
     * @return
     */
    int updateProduct(Product product);

    int updateProductCategoryToNull(long productCategoryId);

    /**
     * 根据productId和shopId删除指定商品
     * @param productId
     * @param shopId
     * @return
     */
    int deleteProduct(@Param("productId") long productId,@Param("shopId") long shopId);
}
