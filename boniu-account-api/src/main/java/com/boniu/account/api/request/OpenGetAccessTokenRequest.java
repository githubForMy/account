package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName OpenGetAccessTokenRequest
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

@ApiModel("获取授权凭证入参")
public class OpenGetAccessTokenRequest {
    @ApiModelProperty(value = "应用唯一标识，由平台方面提供", example = "", required = true)
    private String appId;

    @ApiModelProperty(value = "应用秘钥，由平台方面提供", example = "", required = true)
    private String masterSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
