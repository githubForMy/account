package com.boniu.account.server.common;

import com.boniu.base.utile.exception.ErrorCode;

import static com.boniu.base.utile.exception.ErrorCode.*;

/**
 * 账户系统错误码枚举
 * Created by ZZF on 18/06/12.
 */
public enum AccountErrorEnum {

    /* 接口错误 */
    CHECK_USERNAME_FAILURE(true, "1001", "检查用户名是否已存在失败"),
    REGISTER_ACCOUNT_FAILURE(true, "1002", "注册账户失败"),
    LOGIN_ACCOUNT_FAILURE(true, "1003", "登录账户失败"),
    PASSWORD_NOT_MATCH(true, "1004", "两次输入密码不匹配"),
    ACCOUNT_IS_EXCEPTION(true, "1005", "账户状态异常"),
    LOGOUT_ACCOUNT_FAILURE(true, "1006", "退出登录失败"),
    GET_ACCOUNT_INFO_FAILURE(true, "1007", "获取用户信息失败"),
    GET_NEW_ACCOUNT_ID_FAILURE(true, "1008", "获取登录信息失败"),
    UPDATE_ACCOUNT_FAILURE(true, "1009", "更新用户信息失败"),
    PASSWORD_INCORRECT_FORMAT(true, "1010", "密码格式不正确"),
    USERNAME_IS_EXIST(true, "1011", "用户名已被使用"),
    USERNAME_PWD_ERROR(true, "1012", "用户名或密码错误"),
    USERNAME_IS_NOT_EXIST(true, "1013", "用户名不存在"),
    MODIFY_PASSWORD_FAILURE(true, "1015", "修改账户登录密码失败"),
    OLD_PASSWORD_IS_WRONG(true, "1016", "原密码输入错误"),
    BIND_EMIAL_FAILURE(true, "1017", "绑定邮箱失败"),
    RESET_PASSWORD_FAIL(true, "1018", "重设密码失败"),
    CANCEL_ACCOUNT_FAIL(true, "1019", "注销账户失败"),
    EMAIL_INCORRECT_FORMAT(true, "1020", "邮箱地址格式不正确"),
    VISITOR_ACCOUNT_NOT_EXIST(true, "1021", "游客账户不存在"),
    SAVE_ACCOUNT_FAILURE(true, "1021", "保存账户信息失败"),
    ACCOUNT_NOT_EXIST(true, "1022", "账户不存在"),
    GET_ACCOUNT_INFO_LIST_FAILURE(true, "1023", "获取账户信息列表失败"),
    VIP_EXPIRE_FAILURE(true, "1023", "过期VIP账户失败"),
    CLEAR_CANCEL_TIME_FALURE(true, "1024", "清理申请注销时间失败"),
    GET_PRODUCT_IS_FAILURE(true, "1025", "产品类型异常"),
    ADD_UUID_FAILURE(true, "1026", "上传设备号失败"),
    SAVE_MAIN_ACCOUNT_FAILURE(true, "1027", "保存主账户信息失败"),
    UPDATE_MAIN_ACCOUNT_FAILURE(true, "1028", "更新主账户信息失败"),
    GET_MAIN_ACCOUNT_INFO_FAILURE(true, "1029", "获取主账户相关信息失败"),
    UUID_REGISTER_FAILURE(true, "1030", "游客账户注册失败"),
    GET_CANCEL_APPLY_INFO_FAILURE(true, "1031", "获取注销申请相关信息"),
    APPLY_ACCOUNT_CANCEL_FAILURE(true, "1032", "申请账户注销失败"),
    CANCEL_ACCOUNT_CANCEL_APPLY_FAILURE(true, "1033", "取消账户申请注销失败"),
    ALREADY_APPLY(true, "1034", "当日已提交注销申请，请次日重试"),
    RENEW_VISITOR_ACCOUNT_FAILURE(true, "1034", "恢复游客账户失败"),
    GET_ACCOUNT_SCORE_FAILURE(true, "1035", "获取用户积分信息失败"),
    UPDATE_ACCOUNT_SCORE_FAILURE(true, "1037", "更新用户积分信息失败"),
    /* 开放平台相关 */
    GET_ACCESS_TOKEN_FAILURE(true, "2001", "亲，服务器开小差，请稍后查看"),//获取授权凭证失败
    SECRET_IS_EXCEPTION(true, "2002", "应用唯一标识或密钥无效"),
    AUTHORIZED_LOGIN_FAILURE(true, "2003", "授权登录失败"),
    ACCESS_TOKEN_IS_EXCEPTION(true, "2004", "授权信息不存在或无效，请确认后重试"),
    ACCESS_TOKEN_IS_EXPIRED(true, "2005", "授权凭证已过期，请重新获取"),
    UPDATE_OPEN_ACCOUNT_FAILURE(true, "2006", "更新开放平台数据失败"),
    OPEN_ACCOUNT_IS_NOT_EXIST(true, "2007", "开放平台账户不存在"),
    GET_OPEN_ACCOUNT_FAILURE(true, "2008", "获取开放平台账户信息失败"),

    //会员信息相关
    ALREADY_VIP_ACCOUNT(true, "3001", "当前已经是会员用户，无需重复购买"),

    GET_ACCOUNT_COUPON_DETAIL_FAILURE(true, "3002", "获取优惠券详细信息失败"),
    ADD_ACCOUNT_COUPON_FAILURE(true, "3003", "新增用户优惠券信息失败"),
    UPDATE_ACCOUNT_COUPON_FAILURE(true, "3004", "更新用户优惠券信息失败"),


    // 服务调用类错误码
    CALL_ACCOUNT_FAIL(true, "ZZ02", "亲，服务器开小差，请稍后查看"),//账户服务连接异常
    CALL_MERCHANT_FAIL(true, "ZZ03", "亲，服务器开小差，请稍后查看"),//商家服务连接异常
    CALL_TRADE_FAIL(true, "ZZ04", "亲，服务器开小差，请稍后查看"),//交易服务连接异常
    CALL_PRODUCT_FAIL(true, "ZZ05", "亲，服务器开小差，请稍后查看"),//产品服务连接异常
    CALL_MARKETING_FAIL(true, "ZZ06", "亲，服务器开小差，请稍后查看"),//运营服务连接异常
    CALL_TAOKE_FAIL(true, "ZZ07", "亲，服务器开小差，请稍后查看"),//淘宝客服务连接异常
    CALL_MESSAGE_FAIL(true, "ZZ08", "亲，服务器开小差，请稍后查看"),//消息服务连接异常
    CALL_LOGISTICS_FAIL(true, "ZZ09", "亲，服务器开小差，请稍后查看"),//物流服务连接异常
    CALL_SETTLE_FAIL(true, "ZZ10", "亲，服务器开小差，请稍后查看"),//清算服务连接异常
    CALL_COMMON_FAIL(true, "ZZ11", "亲，服务器开小差，请稍后查看"),//公共服务连接异常
    CALL_SCHEDULE_FAIL(true, "ZZ12", "亲，服务器开小差，请稍后查看"),//调度服务连接异常
    CALL_SEARCH_FAIL(true, "ZZ13", "亲，服务器开小差，请稍后查看"),//搜索服务连接异常
    CALL_SEARCH_WECHAT(true, "ZZ14", "亲，服务器开小差，请稍后查看"),//微信服务连接异常

    // 通用错误码
    PLEASE_REFRESH_ACCOUNTID(true, "9990", "请重新获取"), //accountId失效情况下
    PLEASE_RELOGIN(true, "9991", "请重新登陆"), //token失效情况下
    INVALID_PARAM(true, "0000", "参数异常"),
    DATA_ERROR(true, "9997", "亲，服务器开小差，请稍后查看"),//数据异常
    DB_ERROR(true, "9998", "亲，服务器开小差，请稍后查看"),//数据库操作失败
    DEFAULT(true, "9999", "亲，服务器开小差，请稍后查看");//默认错误

    private ErrorCode errorCode;

    AccountErrorEnum(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    AccountErrorEnum(boolean isShow, String errorCode, String describe) {
        if(isShow){
            this.errorCode = new ErrorCode(SYS_CODE_ACCOUNT, SHOWLEVEL_SHOW, errorCode, describe);
        }else{
            this.errorCode = new ErrorCode(SYS_CODE_ACCOUNT, SHOWLEVEL_NOT_SHOW, errorCode, describe);
        }
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
