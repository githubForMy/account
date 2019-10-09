package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName LoginAccountRequest
 * @Author HanXin
 * @Date 2019-07-02
 */

@ApiModel("账户登录入参")
public class LoginAccountRequest {
    @ApiModelProperty(value = "注册手机号", example = "18888888888")
    private String mobile;

    @ApiModelProperty(value = "APP渠道", example = "STORY")
    private String appName;

    @ApiModelProperty(value = "验证码", example = "181818")
    private String verifyCode;

    @ApiModelProperty(value = "注册渠道", example = "web")
    private String channel;

    @ApiModelProperty(value = "设备唯一识别号", example = "110123456")
    private String deviceId;

    @ApiModelProperty(value = "加密过后的账户ID", example = "")
    private String accountId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
