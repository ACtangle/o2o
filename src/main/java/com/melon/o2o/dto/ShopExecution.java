package com.melon.o2o.dto;

import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ShopExecution
 * @Description
 * @Author melon
 * @Date 2019-08-17 19:29
 * @Version
 */

@Data
public class ShopExecution {
    /**
     * 结果状态
     */
    private int state;

    /**
     * 状态标识
     */
    private String stateInfo;

    /**
     * 店铺数量
     */
    private int count;

    /**
     * 操作的shop(增删改店铺的时候用到)
     */
    private Shop shop;

    /**
     * shop列表(查询店铺列表的时候使用)
     */
    private List<Shop> shopList;

    public ShopExecution() {
    }

    /**
     *
     *
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     *
     *
     * @param stateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }


    /**
     *
     *
     * @param stateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

}