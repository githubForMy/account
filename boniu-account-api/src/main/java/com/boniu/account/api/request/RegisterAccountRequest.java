package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName RegisterAccountRequest
 * @Author HanXin
 * @Date 2019-07-12
 */

@ApiModel("注册用户入参")
public class RegisterAccountRequest extends BaseRequest {
    @ApiModelProperty(value = "用户名(只能为英文和数字)", example = "rhinox", required = true)
    private String userName;

    @ApiModelProperty(value = "第一次输入的密码（长度限制为8-16位）", example = "123456", required = true)
    private String firstPassword;

    @ApiModelProperty(value = "第二次输入的密码（长度限制为8-16位）", example = "123456", required = true)
    private String secondPassword;

    @ApiModelProperty(value = "注册渠道", example = "web", required = false)
    private String channel;

    @ApiModelProperty(value = "设备所在网络环境的ip地址", example = "192.168.10.110", required = true)
    private String ip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
