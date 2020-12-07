package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AccountDetailVO
 * @Description 账户详细信息
 * @Author HanXin
 * @Date 2019-07-12
 */

@ApiModel("账户详细信息")
public class AccountDetailVO {
    @ApiModelProperty(value = "账户id", example = "10155970543370710044", required = true)
    private String accountId;
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;
    @ApiModelProperty(value = "用户手机号码", example = "188888888888", required = true)
    private String mobile;
    @ApiModelProperty(value = "邮箱地址", example = "rhinox@rhinox.cn", required = true)
    private String email;
    @ApiModelProperty(value = "用户昵称", example = "昵称", required = true)
    private String nickname;
    @ApiModelProperty(value = "用户头像链接地址", example = "http://boniustory.oss-cn-hangzhou.aliyuncs.com/operate/20190328/1553754539089.jpg", required = true)
    private String headImg;
    @ApiModelProperty(value = "性别", example = "MALE", required = true)
    private String sexual;
    @ApiModelProperty(value = "宝宝生日", example = "2019-04-07", required = true)
    private Date birthday;
    @ApiModelProperty(value = "个性签名", example = "签名内容", required = true)
    private String autograph;
    @ApiModelProperty(value = "用户邀请码", example = "xDHChRsw", required = true)
    private String inviteCode;
    @ApiModelProperty(value = "邀请人账户id", example = "10155970545070710044", required = true)
    private String inviteAccountId;
    @ApiModelProperty(value = "游客状态下生成的唯一用户识别号", example = "YK10155970945070710044", required = true)
    private String uuid;
    @ApiModelProperty(value = "用户注册时间", example = "2019-04-07 00:00:00", required = true)
    private Date registerTime;
    @ApiModelProperty(value = "账户类型", example = "VIP-付费会员, NORMAL-普通会员", required = true)
    private String type;
    @ApiModelProperty(value = "账户状态", example = "NORMAL-正常, FREEZE-账户已冻结, CANCEL-注销", required = true)
    private String status;
    @ApiModelProperty(value = "是否自动续费", example = "YES-是, NO-否", required = true)
    private String autoPay;
    @ApiModelProperty(value = "会员到期时间", example = "2019-04-07 00:00:00", required = true)
    private Date vipExpireTime;
    @ApiModelProperty(value = "会员有效时间", example = "31", required = true)
    private Integer vipExpireDays;
    @ApiModelProperty(value = "注册渠道", example = "AppStore", required = true)
    private String channel;
    @ApiModelProperty(value = "最后登录ip", example = "192.168.10.62", required = true)
    private String lastLoginIp;
    @ApiModelProperty(value = "最后登录时间", example = "2019-04-03 20:15:21", required = true)
    private Date lastLoginTime;
    @ApiModelProperty(value = "备注信息", example = "备注内容", required = true)
    private String content;
    @ApiModelProperty(value = "用户注销申请时间", example = "2019-04-03 20:15:21", required = true)
    private Date applyCancelTime;
    @ApiModelProperty(value = "账户所属设备", example = "Apple", required = true)
    private String brand;
    @ApiModelProperty(value = "token过期时间", example = "2019-04-03 20:15:21", required = true)
    private Date tokenExpireTime;
    @ApiModelProperty(value = "可消耗的功能使用次数", example = "20", required = true)
    private Integer times;
    @ApiModelProperty(value = "数据统计编号-微贷用", example = "12370192830973408", required = true)
    private String dataId;
    @ApiModelProperty(value = "用户平台", example = "IOS", required = true)
    private String platform;
    @ApiModelProperty(value = "会员所属组别信息", example = "", required = false)
    private List<Map<String, Object>> vipGroupInfos;

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

    public Integer getVipExpireDays() {
        return vipExpireDays;
    }

    public void setVipExpireDays(Integer vipExpireDays) {
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

    public Date getApplyCancelTime() {
        return applyCancelTime;
    }

    public void setApplyCancelTime(Date applyCancelTime) {
        this.applyCancelTime = applyCancelTime;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<Map<String, Object>> getVipGroupInfos() {
        return vipGroupInfos;
    }

    public void setVipGroupInfos(List<Map<String, Object>> vipGroupInfos) {
        this.vipGroupInfos = vipGroupInfos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
