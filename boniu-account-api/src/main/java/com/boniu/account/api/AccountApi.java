package com.boniu.account.api;

import com.boniu.base.utile.message.BaseResponse;

/**
 * @InterfaceName AccountApi
 * @Description 账户相关接口
 * @Author HanXin
 * @Date 2019-06-24
 */

public interface AccountApi {
    /**
     * 检查手机号码是否已注册过APP
     * @param mobile
     * @param appName
     * @return
     */
    BaseResponse<Boolean> checkAccount(String mobile, String appName);
}
