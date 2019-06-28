package com.boniu.account.server.common;

import com.boniu.base.utile.exception.ErrorCode;

import static com.boniu.base.utile.exception.ErrorCode.SYS_CODE_ACCOUNT;
import static com.boniu.base.utile.exception.ErrorCode.*;

/**
 * 账户系统错误码枚举
 * Created by ZZF on 18/06/12.
 */
public enum AccountErrorEnum {

    /* 接口错误 */
    CHECK_ACCOUNT_FAILURE(true, "1001", "亲，网络开小差，请稍后重试"),//检查账户失败

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
