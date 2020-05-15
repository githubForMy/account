package com.boniu.account.server.service;


import com.boniu.account.api.request.AddUuidRequest;

/**
 * @InterfaceName UuidService
 * @Description
 * @Author HanXin
 * @Date 2020-05-15
 */

public interface UuidService {
    /**
     * 新增设备信息
     *
     * @param request
     * @return
     */
    Boolean addUuid(AddUuidRequest request);
}
