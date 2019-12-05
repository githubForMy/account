package com.boniu.account.server.service;

import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.base.utile.message.BaseRequest;

/**
 * @InterfaceName AccountService
 * @Author HanXin
 * @Date 2019-07-02
 */

public interface AccountService {
    /**
     * 账户登录
     *
     * @param request
     * @return
     */
    AccountVO loginAccount(LoginAccountRequest request);

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    AccountVO logoutAccount(BaseRequest request);

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    AccountDetailVO getAccountInfo(BaseRequest request);

    /**
     * token获取新登录信息
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

    /**
     * 验证用户名是否已注册
     *
     * @param request
     * @return
     */
    Boolean checkUserName(CheckUserNameRequest request);

    /**
     * 注册新账户(海外版本)
     *
     * @param request
     * @return
     */
    Boolean registerAccount(RegisterAccountRequest request);

    /**
     * 登录新账户(海外版本)
     *
     * @param request
     * @return
     */
    AccountVO loginOverseasAccount(LoginOverseasAccountRequest request);

    /**
     * 忘记密码找回-重设密码
     *
     * @param request
     * @return
     */
    Boolean resetPassword(ResetPasswordRequest request);
}
