package com.melon.o2o.dto;

import com.melon.o2o.entity.WechatAuth;
import com.melon.o2o.enums.WechatAuthStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @ClassName WechatAuthExecution
 * @Description
 * @Author melon
 * @Date 2019-09-11 11:00
 * @Version
 */
@Data
public class WechatAuthExecution {

    private int state;

    private String stateInfo;

    private int count;

    private WechatAuth wechatAuth;

    private List<WechatAuth> wechatAuthList;

    public WechatAuthExecution(){}

    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum){
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
    }

    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum, WechatAuth wechatAuth){
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
    }

    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum, List<WechatAuth> wechatAuthList){
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
        this.wechatAuthList = wechatAuthList;
    }

}
