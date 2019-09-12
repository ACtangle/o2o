package com.melon.o2o.service.imp;

import com.melon.o2o.cache.JedisUtil;
import com.melon.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName CacheServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-09-12 00:23
 * @Version
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
        for (String key :keySet) {
            jedisKeys.del(key);
        }
    }
}
