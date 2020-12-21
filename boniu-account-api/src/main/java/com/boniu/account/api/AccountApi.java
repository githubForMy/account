package com.boniu.account.api;

import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountCancelVO;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountPushInfoVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.Pagination;

import java.util.List;

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

    /**
     * 通过手机号查询账户是否存在
     *
     * @param request
     * @return
     */
    BaseResponse<AccountVO> queryAccountByMobile(QueryAccountByMobileRequest request);

    /**
     * 保存账户
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> saveAccount(SaveAccountRequest request);

    /**
     * 根据邀请码查询账户信息
     *
     * @param request
     * @return
     */
    BaseResponse<AccountDetailVO> queryAccountByInviteCode(QueryAccountByInviteCodeRequest request);

    /**
     * 根据筛选条件查询账户信息列表
     *
     * @param request
     * @return
     */
    BaseResponse<Pagination<List<AccountDetailVO>>> queryAccountList(QueryAccountListRequest request);

    /**
     * 根据参数分页查询账户信息列表(管理后台)
     *
     * @param request
     * @return
     */
    BaseResponse<Pagination<List<AccountDetailVO>>> queryAccountListForAdmin( QueryAccountListForAdminRequest request);

    /**
     * 根据参数查询用户信息
     *
     * @param request
     * @return
     */
    BaseResponse<List<AccountDetailVO>> queryAccountListBy( QueryAccountListByRequest request);

    /**
     * 注册并登录账户（新）
     *
     * @param request
     * @return
     */
    BaseResponse<AccountVO> registerLoginAccount(RegisterLoginAccountRequest request);

    /**
     * 过期VIP账户为普通账户
     *
     * @return
     */
    BaseResponse<Boolean> vipAccountExpire();

    /**
     * 清理注销申请时间为空
     *
     * @return
     */
    BaseResponse<Boolean> clearCancelTime();

    /**
     * 会员权益消耗
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> consumeTimesOrLength(VipConsumeRequest request);

    /**
     * 更新用户会员信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> updateAccountVipInfo(UpdateAccountVipInfoRequest request);

    /**
     * 取消用户订阅会员信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> cancelAccountAutoVipInfo(CancelAccountAutoVipInfoRequest request);

    /**
     * 获取非会员用户信息
     *
     * @param request
     * @return
     */
    BaseResponse<List<String>> listNormalAccountInfo(BaseRequest request);

    /**
     * 获取推送目标信息
     *
     * @param request
     * @return
     */
    BaseResponse<List<AccountPushInfoVO>> listPushInfo(List<String> request);
}
