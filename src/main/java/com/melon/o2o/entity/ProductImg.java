package com.melon.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName ProductImg
 * @Description
 * @Author melon
 * @Date 2019-08-16 16:16
 * @Version
 */
@Data
public class ProductImg {

    private Long productImgId;

    private String imgAdd;

    private String imgDesc;

    private Integer priority;

    private Date createTime;

    private Long productId;

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgAdd() {
        return imgAdd;
    }

    public void setImgAdd(String imgAdd) {
        this.imgAdd = imgAdd;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
