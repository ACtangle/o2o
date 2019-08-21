package com.melon.o2o.dao.split;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName DynamicDataSourceHolder
 * @Description
 * @Author melon
 * @Date 2019-08-21 22:27
 * @Version
 */


@Slf4j
public class DynamicDataSourceHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static final String DB_MASTER = "master";

    public static final String DB_SLAVE = "slave";

    /**
     * 获取线程dbType
     * @return
     */
    public static Object getDbType() {
        String db = contextHolder.get();
        if (null == db) {
            db = DB_MASTER;
        }
        return db;
    }


    /**
     * 设置线程dbType
     * @param str
     */
    public static void setDbType(String str) {
        log.debug("所使用的的数据源为：" + str);
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDbtype(){
        contextHolder.remove();
    }
}
