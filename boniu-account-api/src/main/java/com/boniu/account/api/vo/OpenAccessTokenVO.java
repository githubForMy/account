package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName AccessTokenResult
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

@ApiModel("授权凭证返参结果")
public class OpenAccessTokenVO {
    @ApiModelProperty(value = "授权凭证", example = "", required = true)
    private String accessToken;
    @ApiModelProperty(value = "授权凭证过期时间", example = "2020-06-10 12:00:00", required = true)
    private String tokenExpireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(String tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
