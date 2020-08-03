package com.boniu.account.api;

import com.boniu.account.api.request.OpenAccountGetRequest;
import com.boniu.account.api.request.OpenAuthorizedLoginRequest;
import com.boniu.account.api.request.OpenGetAccessTokenRequest;
import com.boniu.account.api.request.OpenUpdateAccountRequest;
import com.boniu.account.api.vo.OpenAccessTokenVO;
import com.boniu.account.api.vo.OpenAccountVO;
import com.boniu.account.api.vo.OpenAuthorizedLoginVO;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @InterfaceName AccountOpenApi
 * @Description 账户开放平台相关接口
 * @Author HanXin
 * @Date 2020-06-11
 */

public interface AccountOpenApi {

    /**
     * 获取授权凭证
     *
     * @param request
     * @return
     */
    BaseResponse<OpenAccessTokenVO> getAccessToken(OpenGetAccessTokenRequest request);

    /**
     * 授权登录
     *
     * @param request
     * @return
     */
    BaseResponse<OpenAuthorizedLoginVO> authorizedLogin(OpenAuthorizedLoginRequest request);

    /**
     * 更新开放平台账户数据
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> updateOpenAccount(OpenUpdateAccountRequest request);

    /**
     * 获取开放平台账户数据
     *
     * @param request
     * @return
     */
    BaseResponse<OpenAccountVO> getOpenAccountInfo(OpenAccountGetRequest request);
}
