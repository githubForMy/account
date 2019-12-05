package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName LoginOverseasAccountRequest
 * @Author HanXin
 * @Date 2019-12-05
 */

@ApiModel("海外账户登录入参")
public class LoginOverseasAccountRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    @ApiModelProperty(value = "用户名", example = "rhinox", required = true)
    private String userName;

    @ApiModelProperty(value = "密码", example = "12345678", required = true)
    private String password;

    @ApiModelProperty(value = "设备所在网络环境的ip地址", example = "192.168.10.110", required = false)
    private String ip;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
