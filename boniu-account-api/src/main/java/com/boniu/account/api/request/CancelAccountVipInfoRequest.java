package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description 取消用户会员信息入参
 * @Author hanxin
 * @Date 2020/11/23
 */
@ApiModel("取消用户会员信息入参")
public class CancelAccountVipInfoRequest {
    @ApiModelProperty(value = "手机号", example = "133333", required = true)
    private String mobile;
    @ApiModelProperty(value = "app名称", example = "story", required = true)
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
