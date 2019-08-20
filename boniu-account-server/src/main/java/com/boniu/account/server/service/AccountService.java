package com.boniu.account.server.service;

import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.base.utile.message.BaseRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @InterfaceName AccountService
 * @Author HanXin
 * @Date 2019-07-02
 */

public interface AccountService {
    /**
     * 验证账户是否已注册
     *
     * @param request
     * @return
     */
    Boolean checkAccount(CheckAccountRequest request);

    /**
     * 注册新账户
     *
     * @param request
     * @return
     */
    Boolean registerAccount(RegisterAccountRequest request);

    /**
     * 账户登录
     *
     * @param request
     * @return
     */
    AccountVO loginAccount(LoginAccountRequest request, HttpServletRequest httpServletRequest);

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    Boolean logoutAccount(BaseRequest request);

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    AccountDetailVO getAccountInfo(BaseRequest request);

    /**
     * 获取新的加密accountId
     *
     * @param request
     * @return
     */
    String getNewAccountId(TokenAccountRequest request);

    /**
     * 更新账户信息
     *
     * @param request
     * @return
     */
    Boolean updateAccountInfo(UpdateAccountRequest request);
}
