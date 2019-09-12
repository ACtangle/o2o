package com.melon.o2o.dto;

import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.enums.LocalAuthEnum;
import com.melon.o2o.enums.ProductCategoryEnum;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProductCategoryExecution
 * @Description
 * @Author melon
 * @Date 2019-08-26 05:03
 * @Version
 */
@Data
public class LocalAuthExecution {

    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    private LocalAuth localAuth;

    //列表
    private List<LocalAuth> localAuthList;

    public LocalAuthExecution(){}

    //操作失败生成的构造器
    public LocalAuthExecution(LocalAuthEnum localAuthEnum) {
        this.state = localAuthEnum.getState();
        this.stateInfo = localAuthEnum.getStateInfo();
    }

    //操作成功的构造器
    public LocalAuthExecution(LocalAuthEnum localAuthEnum, LocalAuth localAuth){
        this.state = localAuthEnum.getState();
        this.stateInfo = localAuthEnum.getStateInfo();
        this.localAuth = localAuth;
    }


    //操作成功的构造器
    public LocalAuthExecution(LocalAuthEnum localAuthEnum, List<LocalAuth> localAuthList){
        this.state = localAuthEnum.getState();
        this.stateInfo = localAuthEnum.getStateInfo();
        this.localAuthList = localAuthList;
    }

}
