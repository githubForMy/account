package com.boniu.account.server.service;

import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.*;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.Pagination;

import java.util.List;

/**
 * @InterfaceName AccountService
 * @Author HanXin
 * @Date 2019-07-02
 */

public interface AccountService {
    /**
     * 账户登录
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
     * 修改账户登录密码
     *
     * @param request
     * @return
     */
    Boolean modifyLoginPassword(UpdateLoginPasswordRequest request);

    /**
     * 绑定邮箱地址到账户
     *
     * @param request
     * @return
     */
    Boolean bindEmail(BindEmailRequest request);

    /**
     * 忘记密码找回-重设密码
     *
     * @param request
     * @return
     */
    Boolean resetPassword(ResetPasswordRequest request);

    /**
     * 账户注销
     *
     * @param request
     * @return
     */
    AccountCancelVO cancelAccount(BaseRequest request);

    /**
     * 手机号码查询用户是否存在
     *
     * @param request
     * @return
     */
    AccountVO queryByMobile(QueryAccountByMobileRequest request);

    /**
     * 保存账户
     *
     * @param request
     * @return
     */
    Boolean saveAccount(SaveAccountRequest request);

    /**
     * 查询注册邀请码是否存在
     *
     * @param request
     * @return
     */
    AccountDetailVO queryAccountByInviteCode(QueryAccountByInviteCodeRequest request);

    /**
     * 分页查询账户信息列表
     * @param request
     * @return
     */
    Pagination<List<AccountDetailVO>> queryAccountList(QueryAccountListRequest request);

    /**
     * 注册并登录账户
     *
     * @param request
     * @return
     */
    AccountVO registerLoginAccount(RegisterLoginAccountRequest request);

    /**
     * 过期VIP账户为普通账户
     *
     * @return
     */
    Boolean vipAccountExpire();

    /**
     * 清理注销申请时间为空
     */
    void clearCancelTime();

    /**
     * 根据参数查询用户信息
     *
     * @param request
     * @return
     */
    List<AccountDetailVO> queryAccountListBy(QueryAccountListByRequest request);

    /**
     * 根据参数分页查询账户信息列表(管理后台)
     *
     * @param request
     * @return
     */
    Pagination<List<AccountDetailVO>> queryAccountListForAdmin(QueryAccountListForAdminRequest request);

    /**
     * 获取非会员用户信息
     *
     * @param request
     * @return
     */
    List<String> listNormalAccountInfo(BaseRequest request);

    /**
     * 根据dataIds获取用户信息
     *
     * @param dataIds
     * @return
     */
    List<AccountPushInfoVO> listPushInfo(List<String> dataIds);

    /**
     * 取消用户会员信息
     *
     * @param request
     * @return
     */
    String cancelAccountVipInfo(CancelAccountVipInfoRequest request);

    /**
     * 清除用户信息
     *
     * @param request
     * @return
     */
    String deleteAccount(CancelAccountVipInfoRequest request);

    /**
     * 更新用户积分信息
     *
     * @param request
     * @return
     */
    Boolean updateAccountScore(UpdateAccountScoreRequest request);

    /**
     * 批量获取账户详细信息
     *
     * @param request
     * @return
     */
    List<AccountDetailVO> listAccountInfo(List<BaseRequest> request);

    /**
     * 获取用户会员信息列表
     *
     * @param request
     * @return
     */
    List<AccountVipInfoVO> listAccountVipInfo(BaseRequest request);

    /**
     * 更新用户会员信息表
     *
     * @param request
     * @return
     */
    Boolean updateVipInfo(UpdateVipInfoRequest request);
}
