package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName UpdateLoginPasswordRequest
 * @Author HanXin
 * @Date 2019-12-27
 */

@ApiModel("修改账户密码入参")
public class UpdateLoginPasswordRequest extends BaseRequest {

    @ApiModelProperty(value = "用户名", example = "rhinox123", required = true)
    private String username;

    @ApiModelProperty(value = "原登录密码", example = "12345678", required = true)
    private String oldPassword;

    @ApiModelProperty(value = "第一次输入的新密码", example = "87654321", required = true)
    private String firstNewPassword;

    @ApiModelProperty(value = "第二次输入的新密码", example = "87654321", required = true)
    private String secondNewPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getFirstNewPassword() {
        return firstNewPassword;
    }

    public void setFirstNewPassword(String firstNewPassword) {
        this.firstNewPassword = firstNewPassword;
    }

    public String getSecondNewPassword() {
        return secondNewPassword;
    }

    public void setSecondNewPassword(String secondNewPassword) {
        this.secondNewPassword = secondNewPassword;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
