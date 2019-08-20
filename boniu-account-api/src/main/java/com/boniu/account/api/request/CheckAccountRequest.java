package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName CheckRegisterRequest
 * @Author HanXin
 * @Date 2019-07-11
 */

@ApiModel("检查账户是否已注册入参")
public class CheckAccountRequest {
    @ApiModelProperty(value = "注册手机号", example = "18888888888")
    private String mobile;

    @ApiModelProperty(value = "APP渠道", example = "STORY")
    private String appName;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
