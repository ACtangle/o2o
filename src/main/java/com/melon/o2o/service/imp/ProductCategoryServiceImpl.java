package com.melon.o2o.service.imp;

import com.melon.o2o.dao.ProductCategoryDao;
import com.melon.o2o.dao.ShopDao;
import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ShopStateEnum;
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

    @Override
    public List<ProductCategory> queryProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }
}
