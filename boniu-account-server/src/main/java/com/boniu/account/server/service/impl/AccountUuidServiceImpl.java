package com.boniu.account.server.service.impl;

import com.boniu.account.api.request.AddUuidRequest;
import com.boniu.account.repository.api.AccountUuidMapper;
import com.boniu.account.repository.entity.AccountUuidEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountUuidService;
import com.boniu.base.utile.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName UuidServiceImpl
 * @Description
 * @Author HanXin
 * @Date 2020-05-15
 */
@Service
public class AccountUuidServiceImpl implements AccountUuidService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountUuidMapper accountUuidMapper;

    /**
     * 新增设备信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean addUuid(AddUuidRequest request) {
        AccountUuidEntity accountUuidEntity = new AccountUuidEntity();
        accountUuidEntity.setAppName(request.getAppName());
        accountUuidEntity.setUuid(request.getUuid());
        accountUuidEntity.setBrand(request.getBrand());
        accountUuidEntity.setDeviceModel(request.getDeviceModel());
        accountUuidEntity.setCreateTime(new Date());
        int num = accountUuidMapper.saveUuid(accountUuidEntity);
        if (num != 1) {
            logger.error("#1[新增设备信息]-[插入设备信息失败]-UuidEntity={}", accountUuidEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }
}
