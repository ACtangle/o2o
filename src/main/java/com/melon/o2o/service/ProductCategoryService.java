package com.melon.o2o.service;

import com.melon.o2o.dto.ProductCategoryExecution;
import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.exceptions.ProductCategoryOperationException;
import com.melon.o2o.exceptions.ShopOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName ShopServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:15
 * @Version
 */


public interface ProductCategoryService {

    /**
     * 查询指定某个店铺下的所有商品类别信息
     *
     * @param  shopId
     * @return List<ProductCategory>
     */
    List<ProductCategory> getByShopId(long shopId);


    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     *
     * @param productCategoryList
     * @return productCategoryList
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id置位空，再删除该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}

