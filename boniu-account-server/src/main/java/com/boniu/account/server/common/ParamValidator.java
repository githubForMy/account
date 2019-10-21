package com.boniu.account.server.common;

import com.boniu.account.api.request.*;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.StringUtil;

/**
 * @ClassName ParamValidator
 * @Description 请求入参合法性校验
 * @Author HanXin
 * @Date 2019-07-12
 */

public class ParamValidator {

    /**
     * 校验验证账户入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(CheckAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getMobile())
                && request.getMobile().length() != 11
                && StringUtil.isNotBlank(request.getAppName());
    }

    /**
     * 校验注册账户入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(RegisterAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getMobile())
                && request.getMobile().length() != 11
                && StringUtil.isNotBlank(request.getAppName())
                && StringUtil.isNotBlank(request.getVerifyCode());
    }

    /**
     * 校验账户登录入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(LoginAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getAppName())
                && StringUtil.isNotBlank(request.getUuid())
                && StringUtil.isNotBlank(request.getAccountType());
    }

    /**
     * 校验accountId入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(BaseRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getAccountId())
                && StringUtil.isNotBlank(request.getAppName());
    }

    /**
     * 校验获取新的加密accountId入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(TokenAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getToken())
                && StringUtil.isNotBlank(request.getMobile())
                && request.getMobile().length() != 11
                && request.getToken().length() != 32;
    }

    /**
     * 校验更新账户信息入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(UpdateAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getAccountId())
                && StringUtil.isNotBlank(request.getAppName());
    }

    /**
     * 校验创建游客账户信息入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(CreateVisitorAccountRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getAppName())
                && StringUtil.isNotBlank(request.getUuid());
    }

}
