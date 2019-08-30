package com.melon.o2o.service.imp;

import com.melon.o2o.dao.ProductCategoryDao;
import com.melon.o2o.dao.ProductDao;
import com.melon.o2o.dao.ShopDao;
import com.melon.o2o.dto.ProductCategoryExecution;
import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ProductCategoryEnum;
import com.melon.o2o.enums.ShopStateEnum;
import com.melon.o2o.exceptions.ProductCategoryOperationException;
import com.melon.o2o.exceptions.ShopOperationException;
import com.melon.o2o.service.ProductCategoryService;
import com.melon.o2o.service.ShopService;
import com.melon.o2o.util.ImageUtil;
import com.melon.o2o.util.PageCalculator;
import com.melon.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ShopServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:16
 * @Version
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> queryProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public List<ProductCategory> getByShopId(long shopId) {
        return productCategoryDao.queryByShopId(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int updateCounts = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (updateCounts <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS, productCategoryList);
                }
            }catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }

        } else {
            return new ProductCategoryExecution(ProductCategoryEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        //将此商品类别下的商品的类别id置为空,再进行分类的删除，否则会因为外键关联报错
        //product表有某个分类下的商品，如果删除分类则报错，所以要先对产品下的分类字段进行null赋值再删除分类表
        try {
            int result = productDao.updateProductCategoryToNull(productCategoryId);
            if (result<0){
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
        }
        try{
            int result =  productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if (result <= 0){
                throw new ProductCategoryOperationException("店铺类别删除失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory errorL:" + e.getMessage());
        }
    }
}
