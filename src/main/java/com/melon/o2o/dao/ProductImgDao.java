package com.melon.o2o.dao;

import com.melon.o2o.entity.ProductImg;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Description
 * @Author melon
 * @Date 2019-08-26 22:54
 * @Version
 */

public interface ProductImgDao {

    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImgByProductId(long productId);
}
