package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName AccountMainVO
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */
@ApiModel("主账户信息返回结果")
public class AccountMainVO {
    @ApiModelProperty(value = "主账户编号", example = "101590082981739", required = true)
    private String accountMainId;
    @ApiModelProperty(value = "手机号码", example = "12222222222", required = true)
    private String mobile;
    @ApiModelProperty(value = "昵称", example = "U12121212", required = true)
    private String nickname;
    @ApiModelProperty(value = "头像", example = "http://xxx.xxx.com", required = true)
    private String headImg;
    @ApiModelProperty(value = "累计收益", example = "100000", required = true)
    private Integer totalScore;
    @ApiModelProperty(value = "可用收益", example = "80000", required = true)
    private Integer remainScore;
    @ApiModelProperty(value = "绑定的提现账户", example = "xxxx", required = false)
    private String receivedAccount;
    @ApiModelProperty(value = "提现账户对应的真实姓名", example = "XXX", required = false)
    private String realName;

    public String getAccountMainId() {
        return accountMainId;
    }

    public void setAccountMainId(String accountMainId) {
        this.accountMainId = accountMainId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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
