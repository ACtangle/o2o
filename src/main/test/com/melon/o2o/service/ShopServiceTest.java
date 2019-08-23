package com.melon.o2o.service;

import com.melon.o2o.dao.BaseTest;
import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.Area;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.entity.ShopCategory;
import com.melon.o2o.enums.ShopStateEnum;
import com.melon.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.*;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void modifyShop() throws ShopOperationException,FileNotFoundException{
        Shop shop = shopService.getByShopId(1L);
        File shopImg = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop,inputStream,shopImg.getName());
        System.out.println(shopExecution.getState());
    }

    @Test
    public void addShop() throws ShopOperationException, FileNotFoundException {
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
        shop.setShopName("测试店铺");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
///Users/melon/Pictures/desktopImage/IMG_0077.jpg
        File shopImg = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, inputStream, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());

    }
}