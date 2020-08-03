package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName OpenAuthorizedLoginRequest
 * @Author HanXin
 * @Date 2020-06-08
 */

@ApiModel("授权账户登录入参")
public class OpenAuthorizedLoginRequest {
    @ApiModelProperty(value = "授权凭证", example = "", required = true)
    private String accessToken;
    @ApiModelProperty(value = "手机号码", example = "", required = true)
    private String mobile;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
