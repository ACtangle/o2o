package com.melon.o2o.service;

import com.melon.o2o.dto.LocalAuthExecution;
import com.melon.o2o.entity.LocalAuth;
import com.melon.o2o.exceptions.LocalAuthOperationException;

/**
 * @ClassName LocalAuthService
 * @Description
 * @Author melon
 * @Date 2019-09-12 15:20
 * @Version
 */

public interface LocalAuthService {

    LocalAuth getLocalAuthByUsernameAndPwd(String username,String password);

    LocalAuth getLocalAuthByUserId(long userId);

    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    LocalAuthExecution modifyLocalAuth(Long userId,String username,String password,String newPassword) throws LocalAuthOperationException;
}
