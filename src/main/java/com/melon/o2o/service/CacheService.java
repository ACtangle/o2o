package com.melon.o2o.service;

/**
 * @ClassName CacheService
 * @Description
 * @Author melon
 * @Date 2019-09-11 23:14
 * @Version
 */

public interface CacheService {

    /**
     * 依据key前缀删除匹配该模式下的所有key-value
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);
}
