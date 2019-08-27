package com.melon.o2o.service;

import com.melon.o2o.dao.BaseTest;
import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ProductExecution;
import com.melon.o2o.entity.Product;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ProductStateEnum;
import com.melon.o2o.exceptions.ProductOperationException;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    @Ignore
    public void testAAddProduct() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        Product product = new Product();
        product.setShop(shop);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品描述1");
        product.setPriority(1);
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        //创建缩略图文件流
        File thumbnailFile = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream1 = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), inputStream1);
        //创建两个商品详情图片文件流并将它们添加到详情图片中
        File productImg1 = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream2 = new FileInputStream(productImg1);
        File productImg2 = new File("/Users/melon/Pictures/desktopImage/IMG_4137.jpg");
        InputStream inputStream3 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), inputStream2));
        productImgList.add(new ImageHolder(productImg2.getName(), inputStream3));
        //添加商品验证
        ProductExecution productExecution = productService.addProduct(product, imageHolder, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }

    @Test
    public void testBModifyProduct() throws ProductOperationException,FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(20L);
        Product product = new Product();
        product.setProductId(10L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品描述1");
        product.setPriority(1);
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        //创建缩略图文件流
        File thumbnailFile = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream1 = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), inputStream1);
        //创建两个商品详情图片文件流并将它们添加到详情图片中
        File productImg1 = new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg");
        InputStream inputStream2 = new FileInputStream(productImg1);
        File productImg2 = new File("/Users/melon/Pictures/desktopImage/IMG_4137.jpg");
        InputStream inputStream3 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), inputStream2));
        productImgList.add(new ImageHolder(productImg2.getName(), inputStream3));
        //添加商品验证
        ProductExecution productExecution = productService.modifyProduct(product, imageHolder, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }
}