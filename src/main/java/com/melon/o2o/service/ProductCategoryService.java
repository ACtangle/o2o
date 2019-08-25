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

    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     *
     * @param productCategoryList
     * @return productCategoryList
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}

