package com.boniu.account.server.common;

import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.request.CheckUserNameRequest;
import com.boniu.account.api.request.LoginAccountRequest;
import com.boniu.account.api.request.UpdateAccountRequest;
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
    public static boolean validate(CheckUserNameRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getUserName())
                && request.getUserName().length() != 11
                && StringUtil.isNotBlank(request.getAppName());
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
                && StringUtil.isNotBlank(request.getAccountType())
                && null != AccountTypeEnum.getByCode(request.getAccountType())
                && StringUtil.isNotBlank(request.getIp());
    }

    /**
     * 校验accountId入参的合法性
     *
     * @param request
     * @return
     */
    public static boolean validate(BaseRequest request) {
        return null != request
                && StringUtil.isNotBlank(request.getUuid())
                && StringUtil.isNotBlank(request.getAppName());
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
}
