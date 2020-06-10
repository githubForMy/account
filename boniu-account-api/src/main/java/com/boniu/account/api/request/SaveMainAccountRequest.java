package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName SaveMainAccountRequest
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

@ApiModel("保存主账户相关信息入参")
public class SaveMainAccountRequest {
    @ApiModelProperty(value = "手机号码", example = "12222222222", required = true)
    private String mobile;

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
