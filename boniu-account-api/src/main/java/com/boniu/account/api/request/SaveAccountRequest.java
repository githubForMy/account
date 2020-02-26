package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName SaveAccountRequest
 * @Author HanXin
 * @Date 2020-02-06
 */

@ApiModel("保存账户入参")
public class SaveAccountRequest {
    @ApiModelProperty(value = "账户标号", example = "100988798723", required = true)
    private String accountId;

    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    @ApiModelProperty(value = "手机号码", example = "18888888888", required = true)
    private String mobile;

    @ApiModelProperty(value = "设备唯一识别号", example = "110123456", required = true)
    private String uuid;

    @ApiModelProperty(value = "注册渠道", example = "web", required = true)
    private String channel;

    @ApiModelProperty(value = "账户注册时间", example = "2020-01-01 12:00:00", required = true)
    private Long registerTime;

    @ApiModelProperty(value = "账户类型", example = "NORMAL-普通用户，VIP-会员用户", required = true)
    private String type;

    @ApiModelProperty(value = "账户状态", example = "NORMAL", required = true)
    private String status;

    @ApiModelProperty(value = "会员过期时间", example = "2020-01-01 12:00:00", required = false)
    private Long vipExpireTime;

    @ApiModelProperty(value = "APP本地保存的token", example = "sryb4a9ods9dufursmf3q8ysbin0k25z", required = true)
    private String token;

    @ApiModelProperty(value = "token过期时间", example = "2020-01-01 12:00:00", required = true)
    private Long tokenExpireTime;

    @ApiModelProperty(value = "最后一次登录时间", example = "2020-01-01 12:00:00", required = true)
    private Long lastLoginTime;

    @ApiModelProperty(value = "最后一次登录ip", example = "127.0.0.1", required = true)
    private String lastLoginIp;

    @ApiModelProperty(value = "设备品牌", example = "APPLE", required = true)
    private String brand;

    @ApiModelProperty(value = "设备型号", example = "iPhone7", required = true)
    private String deviceModel;

    @ApiModelProperty(value = "昵称", example = "犀牛实验室", required = false)
    private String nickName;

    @ApiModelProperty(value = "头像", example = "http://xxxx.xxxxxx.xxx", required = false)
    private String headImg;

    @ApiModelProperty(value = "邀请码", example = "lhfslhj98", required = false)
    private String inviteCode;

    @ApiModelProperty(value = "邀请人账户编号", example = "10000009297434", required = false)
    private String inviteAccountId;

    @ApiModelProperty(value = "宝宝性别", example = "男-MALE, 女-FEMALE")
    private String babySex;

    @ApiModelProperty(value = "宝宝生日", example = "2019-04-07")
    private String babyBirthday;

    @ApiModelProperty(value = "个性签名", example = "签名内容")
    private String autograph;

    @ApiModelProperty(value = "创建时间", example = "2020-01-01 12:00:00", required = true)
    private Long createTime;

    @ApiModelProperty(value = "更新时间", example = "2020-01-01 12:00:00", required = false)
    private Long updateTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVipExpireTime() {
        return vipExpireTime;
    }

    public void setVipExpireTime(Long vipExpireTime) {
        this.vipExpireTime = vipExpireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getBabySex() {
        return babySex;
    }

    public void setBabySex(String babySex) {
        this.babySex = babySex;
    }

    public String getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(String babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getInviteAccountId() {
        return inviteAccountId;
    }

    public void setInviteAccountId(String inviteAccountId) {
        this.inviteAccountId = inviteAccountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
