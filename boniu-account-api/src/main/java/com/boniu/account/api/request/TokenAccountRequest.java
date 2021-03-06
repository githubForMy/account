package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName TokenRequest
 * @Author HanXin
 * @Date 2019-07-15
 */

@ApiModel("token获取新的accountId入参")
public class TokenAccountRequest {
    @ApiModelProperty(value = "APP本地保存的token", example = "sryb4a9ods9dufursmf3q8ysbin0k25z", required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
