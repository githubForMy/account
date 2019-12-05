package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName ForgetPasswordRequest
 * @Author HanXin
 * @Date 2019-12-05
 */

@ApiModel("重设密码入参")
public class ResetPasswordRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    @ApiModelProperty(value = "用户名", example = "rhinox", required = true)
    private String userName;

    @ApiModelProperty(value = "第一次输入的密码（长度限制为8-16位）", example = "123456", required = true)
    private String firstPassword;

    @ApiModelProperty(value = "第二次输入的密码（长度限制为8-16位）", example = "123456", required = true)
    private String secondPassword;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
