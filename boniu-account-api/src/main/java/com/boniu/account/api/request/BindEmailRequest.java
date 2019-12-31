package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName BindEmailRequest
 * @Author HanXin
 * @Date 2019-12-27
 */

@ApiModel("账户绑定邮箱入参")
public class BindEmailRequest extends BaseRequest {

    @ApiModelProperty(value = "邮箱地址", example = "service@rhinox.cn", required = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
