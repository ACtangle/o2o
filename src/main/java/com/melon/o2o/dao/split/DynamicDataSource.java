package com.melon.o2o.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName DynamicDataSource,读写分离
 * @Description
 * @Author melon
 * @Date 2019-08-21 22:26
 * @Version
 */


public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return DynamicDataSourceHolder.getDbType();
    }
}
