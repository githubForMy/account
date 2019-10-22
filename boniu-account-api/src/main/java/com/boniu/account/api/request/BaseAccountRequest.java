package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName BaseAccountRequest
 * @Description
 * @Author HanXin
 * @Date 2019-10-22
 */

@ApiModel("账户信息基本入参")
public class BaseAccountRequest {

    @ApiModelProperty(value = "账户ID", example = "t9xIkF2dqjlREJyhpGCK+fOlFUCD8npFNz5A7Xoiz/E=")
    private String accountId;

    @ApiModelProperty(value = "APP渠道")
    private String appName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
