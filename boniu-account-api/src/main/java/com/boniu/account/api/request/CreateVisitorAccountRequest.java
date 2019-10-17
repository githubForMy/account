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
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;
    @ApiModelProperty(value = "游客设备编号", example = "BDAF6B4D-5DC0-4AEF-BCF8-6C7EFC94DE97", required = true)
    private String uuid;
    @ApiModelProperty(value = "ip", example = "0.0.0.0", required = true)
    private String ip;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
