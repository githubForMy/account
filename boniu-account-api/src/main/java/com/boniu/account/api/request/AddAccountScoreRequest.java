package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @description: 新增用户积分信息
 * @author: FengXian
 * @create: 2021-01-06 10:46
 **/
@ApiModel("新增用户积分信息")
public class AddAccountScoreRequest {
    @ApiModelProperty(value = "用户编号", example = "100", required = true)
    private String accountId;
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
