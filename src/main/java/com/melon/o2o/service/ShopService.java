package com.melon.o2o.service;

import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName ShopServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:15
 * @Version
 */


public interface ShopService {

    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}

