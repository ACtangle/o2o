package com.melon.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName ProductCategory
 * @Description
 * @Author melon
 * @Date 2019-08-16 16:06
 * @Version
 */
@Data
public class ProductCategory {

    private Long productCategoryId;

    private Long shopId;

    private String productCategoryName;

    private Integer priority;

    private Date createTime;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
