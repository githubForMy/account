package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 更新主账户信息入参
 * @Author hanxin
 * @Date 2020/9/8
 */

@ApiModel("更新主账户信息入参")
public class UpdateAccountMainRequest {
    @ApiModelProperty(value = "主账户", example = "100238947897468", required = false)
    private String accountMainId;
    @ApiModelProperty(value = "手机号", example = "17777777777", required = true)
    private String mobile;
    @ApiModelProperty(value = "个人中心昵称", example = "昵称", required = false)
    private String nickname;
    @ApiModelProperty(value = "个人中心头像链接地址", example = "https://boniuapp.rhinox.cn/common/head/1599546812784.png", required = false)
    private String headImg;
    @ApiModelProperty(value = "累计获得总积分", example = "10000", required = false)
    private Integer totalScore;
    @ApiModelProperty(value = "当前可用积分", example = "2000", required = false)
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
}
