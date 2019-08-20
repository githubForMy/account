package com.boniu.account.api;

import com.boniu.account.api.request.*;
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
     * 验证手机号码是否已注册
     *
     * @param request
     * @return
     */
    BaseResponse checkAccount(CheckAccountRequest request);

    /**
     * 注册账户
     *
     * @param request
     * @return
     */
    BaseResponse registerAccount(RegisterAccountRequest request);

    /**
     * 登录账户
     *
     * @param request
     * @return
     */
    BaseResponse<AccountVO> loginAccount(LoginAccountRequest request);

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    BaseResponse logoutAccount(BaseRequest request);

    /**
     * 获取账户详细信息
     * @param request
     * @return
     */
    BaseResponse<AccountDetailVO> getAccountInfo(BaseRequest request);

    /**
     * 通过token获取新的加密accountId
     *
     * @param request
     * @return
     */
    BaseResponse getNewAccountId(TokenAccountRequest request);

    /**
     * 更新账户信息
     *
     * @param request
     * @return
     */
    BaseResponse updateAccountInfo(UpdateAccountRequest request);

}
