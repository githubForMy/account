package com.boniu.account.server.service.impl;

import com.boniu.account.api.request.AddUuidRequest;
import com.boniu.account.repository.api.UuidMapper;
import com.boniu.account.repository.entity.UuidEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.UuidService;
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
public class UuidServiceImpl implements UuidService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UuidMapper uuidMapper;

    /**
     * 新增设备信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean addUuid(AddUuidRequest request) {
        UuidEntity uuidEntity = new UuidEntity();
        uuidEntity.setAppName(request.getAppName());
        uuidEntity.setPlatform(request.getPlatform());
        uuidEntity.setUuid(request.getUuid());
        uuidEntity.setBrand(request.getBrand());
        uuidEntity.setDeviceModel(request.getDeviceModel());
        uuidEntity.setCreateTime(new Date());
        int num = uuidMapper.saveUuid(uuidEntity);
        if (num != 1) {
            logger.error("#1[新增设备信息]-[插入设备信息失败]-UuidEntity={}", uuidEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }
}
