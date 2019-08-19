package com.melon.o2o.exceptions;

/**
 * @ClassName ShopOperationException
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:39
 * @Version
 */

public class ShopOperationException extends RuntimeException {


    private static final long serialVersionUID = 2139082584907212633L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
