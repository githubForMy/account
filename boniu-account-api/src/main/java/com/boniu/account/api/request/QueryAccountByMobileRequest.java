package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName QueryAccountByMobileRequest
 * @Author HanXin
 * @Date 2020-02-05
 */

@ApiModel("通过手机号码查询账户信息")
public class QueryAccountByMobileRequest {
    @ApiModelProperty(value = "用户登录手机号", example = "18888888888", required = true)
    private String mobile;

    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
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
