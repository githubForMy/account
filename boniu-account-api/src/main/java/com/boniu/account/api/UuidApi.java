package com.boniu.account.api;

import com.boniu.account.api.request.AddUuidRequest;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @InterfaceName UuidApi
 * @Description
 * @Author HanXin
 * @Date 2020-05-15
 */

public interface UuidApi {
    /**
     * 新增设备信息
     *
     * @param request
     * @return
     */
    BaseResponse add(AddUuidRequest request);
}
