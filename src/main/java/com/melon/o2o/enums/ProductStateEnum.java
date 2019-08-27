package com.melon.o2o.enums;

/**
 * @ClassName ShopStateEnum
 * @Description
 * @Author melon
 * @Date 2019-08-17 19:32
 * @Version
 */
public enum ProductStateEnum {


    SUCCESS(1, "操作成功"),
    EMPTY(-1001,"参数为空"),
    INNER_ERROR(-1002,"内部错误");

    private int state;

    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回enum值
     */
    public static ProductStateEnum stateOf(int state) {
        for (ProductStateEnum stateEnum : values()) {
            if (stateEnum.state == state) {
                return stateEnum;
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
