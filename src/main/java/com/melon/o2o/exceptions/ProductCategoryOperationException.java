package com.melon.o2o.exceptions;

/**
 * @ClassName ProductCategoryOperationException
 * @Description
 * @Author melon
 * @Date 2019-08-26 05:10
 * @Version
 */

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = 8301821607180178043L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
