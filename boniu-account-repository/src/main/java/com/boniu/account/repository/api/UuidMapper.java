package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.UuidEntity;

/**
 * @InterfaceName UuidMapper
 * @Description
 * @Author HanXin
 * @Date 2020-02-26
 */

public interface UuidMapper {
    /**
     * 插入设备信息
     *
     * @param entity
     * @return
     */
    int saveUuid(UuidEntity entity);

}
