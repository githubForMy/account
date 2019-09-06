package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName CreateVisitorAccountRequest
 * @Author HanXin
 * @Date 2019-09-03
 */

@ApiModel("创建游客账户入参")
public class CreateVisitorAccountRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY")
    private String appName;

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
