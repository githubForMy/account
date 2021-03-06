package com.boniu.account.api.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @description: 更新用户积分信息
 * @author: FengXian
 * @create: 2021-01-06 10:46
 **/
@ApiModel("用户积分信息返参实体类")
public class AccountScoreResult {
    @ApiModelProperty(value = "用户编号", example = "100", required = true)
    private String accountId;
    @ApiModelProperty(value = "提现账户", example = "100", required = true)
    private String receivedAccount;
    @ApiModelProperty(value = "提现真实名称", example = "100", required = true)
    private String realName;
    @ApiModelProperty(value = "总积分", example = "0", required = true)
    private Integer totalScore;
    @ApiModelProperty(value = "可用积分", example = "0", required = true)
    private Integer remainScore;
    @ApiModelProperty(value = "提现次数", example = "0", required = true)
    private Integer times;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRemainScore() {
        return remainScore;
    }

    public void setRemainScore(Integer remainScore) {
        this.remainScore = remainScore;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
