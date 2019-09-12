package com.melon.o2o.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melon.o2o.cache.JedisUtil;
import com.melon.o2o.dao.ShopCategoryDao;
import com.melon.o2o.entity.ShopCategory;
import com.melon.o2o.exceptions.ShopOperationException;
import com.melon.o2o.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShopCategoryServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-19 23:12
 * @Version
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    private Logger log = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;


    @Override
    @Transactional
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

        String key = SCLISTKEY;
        List<ShopCategory> shopCategoryList = null;
        ObjectMapper mapper = new ObjectMapper();

        if (shopCategoryCondition == null) {
            //若查询条件为空，则列出所有首页大类，即parentId为空的店铺类别
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            //若parentId不为空，则查询该parentId下的所有子类别
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            //列出所有子类别
            key = key + "_allsecondlevel";
        }

        if (!jedisKeys.exists(key)) {
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new ShopOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            try {
                String jsonString = jedisStrings.get(key);
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ShopOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
