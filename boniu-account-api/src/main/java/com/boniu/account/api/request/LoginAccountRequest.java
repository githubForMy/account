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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
