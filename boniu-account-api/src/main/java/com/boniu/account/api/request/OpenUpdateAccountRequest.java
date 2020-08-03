package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * @ClassName OpenUpdateAccountRequest
 * @Description
 * @Author HanXin
 * @Date 2020-06-15
 */

@ApiModel("更新开放平台账户信息入参")
public class OpenUpdateAccountRequest {
    @ApiModelProperty(value = "开放平台编号", example = "", required = true)
    private String openId;
    @ApiModelProperty(value = "授权凭证", example = "", required = true)
    private String accessToken;
    @ApiModelProperty(value = "总收入", example = "100.00", required = false)
    private BigDecimal totalRevenue;
    @ApiModelProperty(value = "可用余额", example = "100.00", required = false)
    private BigDecimal remainBalance;
    @ApiModelProperty(value = "冻结余额", example = "100.00", required = false)
    private BigDecimal freezeBalance;
    @ApiModelProperty(value = "接收转账的账户", example = "xxxxx@zhifubao.com", required = false)
    private String receivedAccount;
    @ApiModelProperty(value = "接收转账的账户账户真实姓名", example = "真实姓名", required = false)
    private String realName;

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

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getRemainBalance() {
        return remainBalance;
    }

    public void setRemainBalance(BigDecimal remainBalance) {
        this.remainBalance = remainBalance;
    }

    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public String getReceivedAccount() {
        return receivedAccount;
    }

    public void setReceivedAccount(String receivedAccount) {
        this.receivedAccount = receivedAccount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
