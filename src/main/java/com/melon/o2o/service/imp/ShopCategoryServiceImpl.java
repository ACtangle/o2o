package com.melon.o2o.service.imp;

import com.melon.o2o.dao.ShopCategoryDao;
import com.melon.o2o.entity.ShopCategory;
import com.melon.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShopCategoryServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 23:12
 * @Version
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {


    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
