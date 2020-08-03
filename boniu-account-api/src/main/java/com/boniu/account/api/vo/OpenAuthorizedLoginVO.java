package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName LoginResult
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */
@ApiModel("授权账户登录返参结果")
public class OpenAuthorizedLoginVO {
    @ApiModelProperty(value = "授权账户唯一标识", example = "", required = true)
    private String openId;
    @ApiModelProperty(value = "手机号", example = "", required = true)
    private String mobile;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
