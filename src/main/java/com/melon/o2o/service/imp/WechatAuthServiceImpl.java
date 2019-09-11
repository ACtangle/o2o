package com.melon.o2o.service.imp;

import com.melon.o2o.dao.PersonInfoDao;
import com.melon.o2o.dao.WechatAuthDao;
import com.melon.o2o.dto.WechatAuthExecution;
import com.melon.o2o.entity.PersonInfo;
import com.melon.o2o.entity.WechatAuth;
import com.melon.o2o.enums.WechatAuthStateEnum;
import com.melon.o2o.exceptions.WechatAuthOperationException;
import com.melon.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName PersonInfoServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-09-11 10:55
 * @Version
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    private Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {

        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL);
        }

        try {
            wechatAuth.setCreateTime(new Date());
            //如果微信账号里的用户实体的用户id为空，说明为第一次使用平台
            //自动创建用户信息
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int result = personInfoDao.insertPersonInfo(personInfo);
                    if (result <= 0) {
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                } catch (Exception e) {
                    log.error("insertPersonInfo error:" + e.getMessage());
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.getMessage());
                }
            }
            //创建专属于本平台的微信账号
            int result = wechatAuthDao.insertWechatAuth(wechatAuth);
            if (result<=0){
                throw new WechatAuthOperationException("账号创建失败");
            }else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,wechatAuth);
            }
        } catch (Exception e) {
            log.error("insertWechatAuth error:" + e.getMessage());
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.getMessage());
        }
    }
}
