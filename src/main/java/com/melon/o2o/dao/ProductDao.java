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


    int queryProductCount(@Param("productCondition") Product productCondition);

    Product queryProductByProductId(long productId);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    int updateProduct(Product product);

    int updateProductCategoryToNull(long productCategoryId);

    int deleteProduct(@Param("productId") long productId,@Param("shopId") long shopId);
}
