package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName AccountDetailVO
 * @Description 账户详细信息
 * @Author HanXin
 * @Date 2019-07-12
 */

@ApiModel("账户详细信息")
public class AccountDetailVO {
    @ApiModelProperty(value = "账户id", example = "10155970543370710044")
    private String accountId;

    @ApiModelProperty(value = "APP渠道", example = "STORY")
    private String appName;

    @ApiModelProperty(value = "用户手机号码", example = "188888888888")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址", example = "rhinox@rhinox.cn")
    private String email;

    @ApiModelProperty(value = "用户昵称", example = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像链接地址", example = "http://boniustory.oss-cn-hangzhou.aliyuncs.com/operate/20190328/1553754539089.jpg")
    private String headImg;

    @ApiModelProperty(value = "性别", example = "MALE")
    private String sexual;

    @ApiModelProperty(value = "宝宝生日", example = "2019-04-07")
    private Date birthday;

    @ApiModelProperty(value = "个性签名", example = "签名内容")
    private String autograph;

    @ApiModelProperty(value = "用户邀请码", example = "xDHChRsw")
    private String inviteCode;

    @ApiModelProperty(value = "邀请人账户id", example = "10155970545070710044")
    private String inviteAccountId;

    @ApiModelProperty(value = "游客状态下生成的唯一用户识别号", example = "YK10155970945070710044")
    private String uuid;

    @ApiModelProperty(value = "用户注册时间", example = "2019-04-07 00:00:00")
    private Date registerTime;

    @ApiModelProperty(value = "账户类型", example = "VIP-付费会员, NORMAL-普通会员")
    private String type;

    @ApiModelProperty(value = "账户状态", example = "NORMAL-正常, FREEZE-账户已冻结, CANCEL-注销")
    private String status;

    @ApiModelProperty(value = "是否自动续费", example = "YES-是, NO-否")
    private String autoPay;

    @ApiModelProperty(value = "会员到期时间", example = "2019-04-07 00:00:00")
    private Date vipExpireTime;

    @ApiModelProperty(value = "会员有效时间", example = "31")
    private int vipExpireDays;

    @ApiModelProperty(value = "注册渠道", example = "AppStore")
    private String channel;

    @ApiModelProperty(value = "最后登录ip", example = "192.168.10.62")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登录时间", example = "2019-04-03 20:15:21")
    private Date lastLoginTime;

    @ApiModelProperty(value = "备注信息", example = "备注内容")
    private String content;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInviteAccountId() {
        return inviteAccountId;
    }

    public void setInviteAccountId(String inviteAccountId) {
        this.inviteAccountId = inviteAccountId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
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

    public String getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(String autoPay) {
        this.autoPay = autoPay;
    }

    public Date getVipExpireTime() {
        return vipExpireTime;
    }

    public void setVipExpireTime(Date vipExpireTime) {
        this.vipExpireTime = vipExpireTime;
    }

    public int getVipExpireDays() {
        return vipExpireDays;
    }

    public void setVipExpireDays(int vipExpireDays) {
        this.vipExpireDays = vipExpireDays;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
