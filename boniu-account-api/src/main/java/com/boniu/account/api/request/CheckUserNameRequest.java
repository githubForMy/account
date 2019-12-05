package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName CheckUserNameRequest
 * @Author HanXin
 * @Date 2019-07-11
 */

@ApiModel("检查用户名是否已注册入参")
public class CheckUserNameRequest {
    @ApiModelProperty(value = "用户名", example = "rhinox", required = true)
    private String userName;

    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
