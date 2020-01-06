package com.boniu.account.api;

import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountCancelVO;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @InterfaceName AccountApi
 * @Description 账户相关接口
 * @Author HanXin
 * @Date 2019-06-24
 */

public interface AccountApi {

    /**
     * 登录账户
     * @param request
     * @return
     */
    BaseResponse<AccountVO> loginAccount(LoginAccountRequest request);

    /**
     * 注销登录
     * @param request
     * @return
     */
    BaseResponse<AccountVO> logoutAccount(BaseRequest request);

    /**
     * 获取账户详细信息
     * @param request
     * @return
     */
    BaseResponse<AccountDetailVO> getAccountInfo(BaseRequest request);

    /**
     * 通过token获取新的加密accountId
     * @param request
     * @return
     */
    BaseResponse<String> getNewAccountId(TokenAccountRequest request);

    /**
     * 更新账户信息
     * @param request
     * @return
     */
    BaseResponse<Boolean> updateAccountInfo(UpdateAccountRequest request);

    /**
     * 验证用户名是否已注册
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> checkUserName(CheckUserNameRequest request);

    /**
     * 注册账户（海外版本）
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> registerAccount(RegisterAccountRequest request);

    /**
     * 登录账户（海外版本）
     *
     * @param request
     * @return
     */
    BaseResponse<AccountVO> loginOverseasAccount(LoginOverseasAccountRequest request);

    /**
     * 修改账户登录密码
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> modifyLoginPassword(UpdateLoginPasswordRequest request);

    /**
     * 绑定邮箱地址到账户
     *
     * @return
     */
    BaseResponse<Boolean> bindEmail(BindEmailRequest request);

    /**
     * 忘记密码找回-重设密码
     *
     * @return
     */
    BaseResponse<Boolean> resetPassword(ResetPasswordRequest request);

    /**
     * 账户注销
     *
     * @param request
     * @return
     */
    BaseResponse<AccountCancelVO> cancelAccount(BaseRequest request);

}
