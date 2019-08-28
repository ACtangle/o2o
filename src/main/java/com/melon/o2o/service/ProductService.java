package com.melon.o2o.service;

import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ProductExecution;
import com.melon.o2o.entity.Product;
import com.melon.o2o.exceptions.ProductOperationException;


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
     *
     * @param product
     * @param thumbnail
     * @param thumbnaiList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> thumbnaiList) throws ProductOperationException;

    /**
     * 查询商品列表分页，可输入的条件有：商品名（模糊），商品状态，店铺Id，商品类别
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 根据商品id查询指定商品
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 编辑商品
     * @param product
     * @param thumbnail
     * @param thumbnaiList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> thumbnaiList) throws ProductOperationException;
}
