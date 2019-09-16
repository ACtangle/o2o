package com.melon.o2o.dao;

import com.melon.o2o.entity.Product;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testBQueryProductList() {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productCondition.setShop(shop);
        List<Product> productList =  productDao.queryProductList(productCondition,0,10);
        System.out.println(productList.get(0).getProductId());
        assertEquals(3,productList.size());
    }

    @Test
    public void testCQueryProductCount() {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        productCondition.setShop(shop);
        int result = productDao.queryProductCount(productCondition);
        assertEquals(3,result);
    }

    @Test
    public void testDQueryProductByProductId() {
        Product product = null;
        product = productDao.queryProductByProductId(16L);
        for (int i = 0;i<product.getProductImgList().size();i++){
            System.out.println(product.getProductImgList().get(i).getImgAddr());
        }
        assertNotEquals(null,product);
    }

    @Test
    public void testAInsertProduct() throws Exception {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryId(16L);
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试1");
        product1.setImgAddr("1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(productCategory1);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试2");
        product2.setImgAddr("2");
        product2.setPriority(1);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(productCategory1);

        Product product3 = new Product();
        product3.setProductName("测试3");
        product3.setProductDesc("测试3");
        product3.setImgAddr("3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(productCategory1);

        int result = productDao.insertProduct(product1);
        assertEquals(1, result);
        int result1 = productDao.insertProduct(product2);
        assertEquals(1, result1);
        int result2 = productDao.insertProduct(product3);
        assertEquals(1, result2);
    }

    @Test
    public void updateProduct() {
        Product product = productDao.queryProductByProductId(6L);
        product.setProductName("修改测试1");
        productDao.updateProduct(product);
    }

    @Test
    public void updateProductCategoryToNull() {
        int result = productDao.updateProductCategoryToNull(17L);
        assertEquals(1,result);
    }

    @Test
    public void deleteProduct() {
        int result = productDao.deleteProduct(6L,1L);
        assertEquals(1,result);
    }
}