package com.melon.o2o.exceptions;

/**
 * @ClassName WechatAuthOperationException
 * @Description
 * @Author melon
 * @Date 2019-09-11 11:00
 * @Version
 */

public class WechatAuthOperationException extends RuntimeException {

    private static final long serialVersionUID = 9086792737091831934L;

    public WechatAuthOperationException(String message){
        super(message);
    }
}
