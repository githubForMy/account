package com.boniu.account.server.common;

import com.boniu.base.utile.exception.ErrorCode;

import static com.boniu.base.utile.exception.ErrorCode.*;

/**
 * 账户系统错误码枚举
 * Created by ZZF on 18/06/12.
 */
public enum AccountErrorEnum {

    /* 接口错误 */
    CHECK_USERNAME_FAILURE(true, "1001", "检查用户名是否已存在失败", "Failed to check if the username already exists"),
    REGISTER_ACCOUNT_FAILURE(true, "1002", "注册账户失败", "Account registration failed"),
    LOGIN_ACCOUNT_FAILURE(true, "1003", "登录账户失败", "Login account failed"),
    PASSWORD_NOT_MATCH(true, "1004", "两次输入密码不匹配", "The two passwords do not match"),
    ACCOUNT_IS_EXCEPTION(true, "1005", "账户状态异常", "Account status is abnormal"),
    LOGOUT_ACCOUNT_FAILURE(true, "1006", "退出登录失败", "Failed to log out"),
    GET_ACCOUNT_INFO_FAILURE(true, "1007", "获取用户信息失败", "Failed to obtain user information"),
    GET_NEW_ACCOUNT_ID_FAILURE(true, "1008", "获取登录信息失败", "Failed to get login information"),
    UPDATE_ACCOUNT_FAILURE(true, "1009", "更新用户信息失败", "Failed to update user information"),
    PASSWORD_INCORRECT_FORMAT(true, "1010", "密码格式不正确", "Incorrect password format"),
    USERNAME_IS_EXIST(true, "1011", "用户名已被使用", "Username is already in use"),
    USERNAME_PWD_ERROR(true, "1012", "用户名或密码错误", "Wrong username or password"),
    USERNAME_IS_NOT_EXIST(true, "1013", "用户名不存在", "Username does not exist"),
    MODIFY_PASSWORD_FAILURE(true, "1015", "修改账户登录密码失败", "Failed to modify the account login password"),
    OLD_PASSWORD_IS_WRONG(true, "1016", "原密码输入错误", "The original password was entered incorrectly"),
    BIND_EMIAL_FAILURE(true, "1017", "绑定邮箱失败", "Failed to bind mailbox"),
    RESET_PASSWORD_FAIL(true, "1018", "重设密码失败", "Failed to reset password"),
    CANCEL_ACCOUNT_FAIL(true, "1019", "注销账户失败", "Failed to cancel account"),
    EMAIL_INCORRECT_FORMAT(true, "1020", "邮箱地址格式不正确", "Email address format is incorrect"),
    VISITOR_ACCOUNT_NOT_EXIST(true, "1021", "游客账户不存在", "Tourist account does not exist"),
    SAVE_ACCOUNT_FAILURE(true, "1021", "保存账户信息失败", "Failed to save account information"),
    ACCOUNT_NOT_EXIST(true, "1022", "账户不存在", "Account does not exist"),
    GET_ACCOUNT_INFO_LIST_FAILURE(true, "1023", "获取账户信息列表失败", "Failed to get account information list"),
    VIP_EXPIRE_FAILURE(true, "1023", "过期VIP账户失败", "Failed to expire VIP account"),
    CLEAR_CANCEL_TIME_FALURE(true, "1024", "清理申请注销时间失败", "Failed to clear the application cancellation time"),
    GET_PRODUCT_IS_FAILURE(true, "1025", "产品类型异常", "Product type is abnormal"),
    ADD_UUID_FAILURE(true, "1026", "上传设备号失败", "Failed to upload device id"),
    SAVE_MAIN_ACCOUNT_FAILURE(true, "1027", "保存主账户信息失败", "Failed to save master account information"),
    UPDATE_MAIN_ACCOUNT_FAILURE(true, "1028", "更新主账户信息失败", "Failed to save master account information"),
    GET_MAIN_ACCOUNT_INFO_FAILURE(true, "1029", "获取主账户相关信息失败", "Failed to obtain information about the master account"),
    UUID_REGISTER_FAILURE(true, "1030", "游客账户注册失败", "Failed to obtain information about the master account"),
    GET_CANCEL_APPLY_INFO_FAILURE(true, "1031", "获取注销申请相关信息失败", "Failed to obtain information about cancellation application"),
    APPLY_ACCOUNT_CANCEL_FAILURE(true, "1032", "申请账户注销失败", "Failed to apply for account cancellation"),
    CANCEL_ACCOUNT_CANCEL_APPLY_FAILURE(true, "1033", "取消账户申请注销失败", "Cancel account cancellation application failed"),
    ALREADY_APPLY(true, "1034", "24小时内已提交注销申请，请稍候重试", "The cancellation request has been submitted within 24 hours, please try again later"),
    CANCEL_ACCOUNT_AUDITING(true, "1035", "注销申请已在审核中，请耐心等待", "The cancellation application is under review, please wait patiently"),
    RENEW_VISITOR_ACCOUNT_FAILURE(true, "1036", "恢复游客账户失败", "Failed to restore guest account"),
    GET_ACCOUNT_SCORE_FAILURE(true, "1037", "获取用户积分信息失败", "Failed to obtain user points information"),
    UPDATE_ACCOUNT_SCORE_FAILURE(true, "1038", "更新用户积分信息失败", "Failed to update user points information"),
    GET_ACCOUNT_VIP_INFO_FAILURE(true, "1038", "获取用户会员信息列表失败", "Failed to get user member information list"),
    UPDATE_ACCOUNT_VIP_INFO_FAILURE(true, "1039", "更新用户会员信息失败", "Failed to update user membership information"),
    LIMIT_TIMES_NOT_ENOUGH(true, "1040", "会员权益剩余次数不足", "Insufficient number of remaining membership benefits"),
    CANCEL_ACCOUNT_FAILURE(true, "1041", "注销用户失败", "Failed to cancel account"),


    /* 开放平台相关 */
    GET_ACCESS_TOKEN_FAILURE(true, "2001", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//获取授权凭证失败
    SECRET_IS_EXCEPTION(true, "2002", "应用唯一标识或密钥无效", "Application unique ID or key is invalid"),
    AUTHORIZED_LOGIN_FAILURE(true, "2003", "授权登录失败", "Authorized login failed"),
    ACCESS_TOKEN_IS_EXCEPTION(true, "2004", "授权信息不存在或无效，请确认后重试", "The authorization information does not exist or is invalid, please confirm and try again"),
    ACCESS_TOKEN_IS_EXPIRED(true, "2005", "授权凭证已过期，请重新获取", "The authorization certificate has expired, please obtain it again"),
    UPDATE_OPEN_ACCOUNT_FAILURE(true, "2006", "更新开放平台数据失败", "Failed to update open platform data"),
    OPEN_ACCOUNT_IS_NOT_EXIST(true, "2007", "开放平台账户不存在", "Open platform account does not exist"),
    GET_OPEN_ACCOUNT_FAILURE(true, "2008", "获取开放平台账户信息失败", "Failed to obtain open platform account information"),

    //会员信息相关
    ALREADY_VIP_ACCOUNT(true, "3001", "当前已经是会员用户，无需重复购买", "Currently already a member user, no need to repeat purchase"),

    GET_ACCOUNT_COUPON_DETAIL_FAILURE(true, "3002", "获取优惠券详细信息失败", "Failed to get coupon details"),
    ADD_ACCOUNT_COUPON_FAILURE(true, "3003", "新增用户优惠券信息失败", "Failed to add user coupon information"),
    UPDATE_ACCOUNT_COUPON_FAILURE(true, "3004", "更新用户优惠券信息失败", "Failed to update user coupon information"),


    // 服务调用类错误码
    CALL_ACCOUNT_FAIL(true, "ZZ02", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//账户服务连接异常
    CALL_MERCHANT_FAIL(true, "ZZ03", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//商家服务连接异常
    CALL_TRADE_FAIL(true, "ZZ04", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//交易服务连接异常
    CALL_PRODUCT_FAIL(true, "ZZ05", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//产品服务连接异常
    CALL_MARKETING_FAIL(true, "ZZ06", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//运营服务连接异常
    CALL_TAOKE_FAIL(true, "ZZ07", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//淘宝客服务连接异常
    CALL_MESSAGE_FAIL(true, "ZZ08", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//消息服务连接异常
    CALL_LOGISTICS_FAIL(true, "ZZ09", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//物流服务连接异常
    CALL_SETTLE_FAIL(true, "ZZ10", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//清算服务连接异常
    CALL_COMMON_FAIL(true, "ZZ11", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//公共服务连接异常
    CALL_SCHEDULE_FAIL(true, "ZZ12", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//调度服务连接异常
    CALL_SEARCH_FAIL(true, "ZZ13", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//搜索服务连接异常
    CALL_SEARCH_WECHAT(true, "ZZ14", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//微信服务连接异常

    // 通用错误码
    PLEASE_REFRESH_ACCOUNTID(true, "9990", "请重新获取", "Please re-acquire"), //accountId失效情况下
    PLEASE_RELOGIN(true, "9991", "请重新登陆", "Please log in again"), //token失效情况下
    INVALID_PARAM(true, "0000", "参数异常", "Parameter abnormal"),
    DATA_ERROR(true, "9997", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//数据异常
    DB_ERROR(true, "9998", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later"),//数据库操作失败
    DEFAULT(true, "9999", "亲，服务器开小差，请稍后查看", "Dear, the server has gone away, please check later");//默认错误

    private ErrorCode errorCode;

    AccountErrorEnum(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    AccountErrorEnum(boolean isShow, String errorCode, String describe, String describeEn) {
        if (isShow) {
            this.errorCode = new ErrorCode(SYS_CODE_ACCOUNT, SHOWLEVEL_SHOW, errorCode, describe, describeEn);
        } else {
            this.errorCode = new ErrorCode(SYS_CODE_ACCOUNT, SHOWLEVEL_NOT_SHOW, errorCode, describe, describeEn);
        }
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
