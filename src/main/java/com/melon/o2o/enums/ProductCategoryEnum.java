package com.melon.o2o.enums;

/**
 * @ClassName ProductCategoryEnum
 * @Description
 * @Author melon
 * @Date 2019-08-25 02:39
 * @Version
 */
public enum ProductCategoryEnum {

    INNER_ERROR(0,"内部系统错误");

    private int state;

    private String stateInfo;

    private ProductCategoryEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回enum值
     */
    public static ProductCategoryEnum stateOf(int state) {
        for (ProductCategoryEnum productCategoryEnum : values()) {
            if (productCategoryEnum.state == state) {
                return productCategoryEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
