package com.melon.o2o.exceptions;

/**
 * @ClassName ProductCategoryOperationException
 * @Description
 * @Author melon
 * @Date 2019-08-26 05:10
 * @Version
 */

public class ProductOperationException extends RuntimeException {


    private static final long serialVersionUID = -1120643967250647801L;

    public ProductOperationException(String msg){
        super(msg);
    }
}
