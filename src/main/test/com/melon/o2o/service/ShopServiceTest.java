package com.melon.o2o.service;

import com.melon.o2o.dao.BaseTest;
import com.melon.o2o.entity.Area;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.entity.ShopCategory;
import com.melon.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;


    @Test
    public void addShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
///Users/melon/Pictures/desktopImage/IMG_0077.jpg
//        CommonsMultipartFile file = new CommonsMultipartFile("/Users/melon/Pictures/desktopImage")
    }
}