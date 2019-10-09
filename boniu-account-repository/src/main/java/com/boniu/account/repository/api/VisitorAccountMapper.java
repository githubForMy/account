package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.VisitorAccountEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName VisitorAccountMapper
 * @Author HanXin
 * @Date 2019-10-09
 */

public interface VisitorAccountMapper {
    /**
     * 通过设备唯一识别码和APP渠道查询游客账户
     *
     * @param mobile
     * @param appName
     * @return
     */
    VisitorAccountEntity selectByDeviceIdAndAppName(@Param("deviceId") String deviceId, @Param("appName") String appName, @Param("status") String status);

    /**
     * 插入游客账户数据信息
     *
     * @param entity
     * @return
     */
    int saveVisitorAccount(VisitorAccountEntity entity);

    /**
     * 更新游客账户数据信息
     *
     * @param entity
     * @return
     */
    int updateVisitorAccount(VisitorAccountEntity entity);
}
