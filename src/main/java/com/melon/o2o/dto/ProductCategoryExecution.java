package com.melon.o2o.dto;

import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.enums.ProductCategoryEnum;

import java.util.List;

/**
 * @ClassName ProductCategoryExecution
 * @Description
 * @Author melon
 * @Date 2019-08-26 05:03
 * @Version
 */

public class ProductCategoryExecution {

    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //列表
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){}

    //操作失败生成的构造器
    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum) {
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
    }

    //操作成功的构造器
    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum,List<ProductCategory> productCategoryList){
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
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

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
