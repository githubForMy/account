package com.boniu.account.server.service;

import com.boniu.account.api.request.OpenAccountGetRequest;
import com.boniu.account.api.request.OpenAuthorizedLoginRequest;
import com.boniu.account.api.request.OpenGetAccessTokenRequest;
import com.boniu.account.api.request.OpenUpdateAccountRequest;
import com.boniu.account.api.vo.OpenAccessTokenVO;
import com.boniu.account.api.vo.OpenAccountVO;
import com.boniu.account.api.vo.OpenAuthorizedLoginVO;

/**
 * @InterfaceName AccountOpenService
 * @Description
 * @Author HanXin
 * @Date 2020-06-11
 */

public interface AccountOpenService {

    /**
     * 获取授权凭证
     *
     * @param request
     * @return
     */
    OpenAccessTokenVO getAccessToken(OpenGetAccessTokenRequest request);

    /**
     * 授权登录
     *
     * @param request
     * @return
     */
    OpenAuthorizedLoginVO authorizedLogin(OpenAuthorizedLoginRequest request);

    /**
     * 更新开放平台账户数据
     *
     * @param request
     */
    void updateOpenAccount(OpenUpdateAccountRequest request);

    /**
     * 获取开放平台账户数据
     *
     * @param request
     * @return
     */
    OpenAccountVO getOpenAccountInfo(OpenAccountGetRequest request);
}
