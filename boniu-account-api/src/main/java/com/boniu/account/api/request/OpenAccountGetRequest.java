package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName OpenAccountGetRequest
 * @Description
 * @Author HanXin
 * @Date 2020-06-15
 */

@ApiModel("获取开放平台信息入参")
public class OpenAccountGetRequest {
    @ApiModelProperty(value = "开放平台编号", example = "", required = true)
    private String openId;
    @ApiModelProperty(value = "授权凭证", example = "", required = true)
    private String accessToken;
    @ApiModelProperty(value = "接收转账的账户", example = "", required = false)
    private String receivedAccount;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getReceivedAccount() {
        return receivedAccount;
    }

    public void setReceivedAccount(String receivedAccount) {
        this.receivedAccount = receivedAccount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
