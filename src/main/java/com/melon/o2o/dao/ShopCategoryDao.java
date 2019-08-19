package com.melon.o2o.dao;

import com.melon.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ShopCategoryDao
 * @Description
 * @Author melon
 * @Date 2019-08-19 22:46
 * @Version
 */

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
