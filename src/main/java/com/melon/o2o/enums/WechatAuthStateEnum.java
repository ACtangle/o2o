package com.melon.o2o.enums;

/**
 * @ClassName ShopStateEnum
 * @Description
 * @Author melon
 * @Date 2019-08-17 19:32
 * @Version
 */
public enum WechatAuthStateEnum {

    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL(-1002,"为空");
    private int state;

    private String stateInfo;

    private WechatAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回enum值
     */
    public static WechatAuthStateEnum stateOf(int state) {
        for (WechatAuthStateEnum stateEnum : values()) {
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
