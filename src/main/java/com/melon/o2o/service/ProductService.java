package com.melon.o2o.service;

import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ProductExecution;
import com.melon.o2o.entity.Product;
import com.melon.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName ProductService
 * @Description
 * @Author melon
 * @Date 2019-08-27 01:49
 * @Version
 */

public interface ProductService {


    /**
     * 新增商品
     * 1.商品的添加
     * 2.商品缩略图
     * 3.商品详情图（list）
     * @param product
     * @param thumbnail
     * @param thumbnaiList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                                List<ImageHolder> thumbnaiList
                                ) throws ProductOperationException;
}
