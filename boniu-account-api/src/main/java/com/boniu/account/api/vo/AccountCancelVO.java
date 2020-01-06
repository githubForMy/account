package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName AccountCancelVO
 * @Description 账户注销返回信息
 * @Author HanXin
 * @Date 2020-01-06
 */

@ApiModel("账户注销返回信息")
public class AccountCancelVO {
    @ApiModelProperty(value = "手机号码，客户端脱敏", example = "18888888888")
    private String mobile;

    @ApiModelProperty(value = "申请时间", example = "2020-01-01 12：00：00")
    private String applyTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
