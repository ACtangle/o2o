package com.melon.o2o.dto;

import com.melon.o2o.entity.Product;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.enums.ProductCategoryEnum;
import com.melon.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * @ClassName ProductCategoryExecution
 * @Description
 * @Author melon
 * @Date 2019-08-26 05:03
 * @Version
 */

public class ProductExecution {

    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //实体
    private Product product;

    //列表
    private List<Product> productList;

    public ProductExecution(){}

    //操作失败生成的构造器
    public ProductExecution(ProductStateEnum productEnum) {
        this.state = productEnum.getState();
        this.stateInfo = productEnum.getStateInfo();
    }

    //操作成功的构造器
    public ProductExecution(ProductStateEnum productEnum, List<Product> productList){
        this.state = productEnum.getState();
        this.stateInfo = productEnum.getStateInfo();
        this.productList = productList;
    }

    //操作成功的构造器
    public ProductExecution(ProductStateEnum productEnum, Product product){
        this.state = productEnum.getState();
        this.stateInfo = productEnum.getStateInfo();
        this.product = product;
    }



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
