package com.melon.o2o.service.imp;

import com.melon.o2o.dao.LocalAuthDao;
import com.melon.o2o.dto.LocalAuthExecution;
import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.enums.LocalAuthEnum;
import com.melon.o2o.exceptions.LocalAuthOperationException;
import com.melon.o2o.service.LocalAuthService;
import com.melon.o2o.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName LocalAuthServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-09-12 15:20
 * @Version
 */

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    private Logger log = LoggerFactory.getLogger(LocalAuthServiceImpl.class);

    @Autowired
    private LocalAuthDao localAuthDao;


    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {

        //空值判断
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
                || localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }

        //查询此用户是否已绑定过平台账号
        LocalAuth tempLocalAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
        if (tempLocalAuth != null) {
            return new LocalAuthExecution(LocalAuthEnum.ONLY_ONE_COUNT);
        }

        try {
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int result = localAuthDao.insertLocalAuth(localAuth);
            if (result < 0) {
                log.error("账号绑定失败");
                throw new LocalAuthOperationException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("bindLocalAuth error:" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        //空值判断
        if (userId != null || username != null || password != null
                || newPassword != null || !password.equals(newPassword)) {
            try {
                //更新密码
                int result = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password), MD5.getMd5(newPassword), new Date());
                if (result < 0) {
                    throw new LocalAuthOperationException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("modifyLocalAuth error:" + e.getMessage());
            }
        }else {
            return new LocalAuthExecution(LocalAuthEnum.NULL_AUTH_INFO);
        }
    }
}
