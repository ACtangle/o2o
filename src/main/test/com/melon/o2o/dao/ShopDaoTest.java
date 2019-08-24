package com.melon.o2o.dao;

import com.melon.o2o.entity.Area;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;


    @Test
    public void queryShopListAndCount(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        assertEquals(5,shopList.size());
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println(count);
    }

    @Test
    public void queryByShopId() {
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
    }

    @Test
    public void insertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area  = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺9");
        shop.setShopDesc("test9");
        shop.setShopAddr("test9");
        shop.setPhone("test9");
        shop.setShopImg("test9");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");

        int result = shopDao.insertShop(shop);
        assertEquals(1,result);
    }

    @Test
    public void updateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("测试描述");
        shop.setShopDesc("测试地址");
        shop.setLastEditTime(new Date());
        int result = shopDao.updateShop(shop);
        assertEquals(1,result);
    }
}