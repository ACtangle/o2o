package com.melon.o2o.util;

/**
 * @ClassName PageCalculator
 * @Description
 * @Author melon
 * @Date 2019-08-24 22:18
 * @Version
 */

public class PageCalculator {

    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0 ? (pageIndex-1) * pageSize :0);
    }
}
