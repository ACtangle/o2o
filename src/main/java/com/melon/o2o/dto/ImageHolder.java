package com.melon.o2o.dto;

import java.io.InputStream;

/**
 * @ClassName ImageHolder 封装图片的属性类
 * @Description
 * @Author melon
 * @Date 2019-08-27 01:57
 * @Version
 */

public class ImageHolder {

    private String imageName;
    private InputStream image;

    public ImageHolder(String imageName, InputStream image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
