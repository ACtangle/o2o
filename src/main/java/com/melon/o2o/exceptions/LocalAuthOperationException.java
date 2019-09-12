package com.melon.o2o.exceptions;

/**
 * @ClassName ShopOperationException
 * @Description
 * @Author melon
 * @Date 2019-08-19 01:39
 * @Version
 */

public class LocalAuthOperationException extends RuntimeException {


    private static final long serialVersionUID = 5399357952388277812L;

    public LocalAuthOperationException(String msg) {
        super(msg);
    }
}
