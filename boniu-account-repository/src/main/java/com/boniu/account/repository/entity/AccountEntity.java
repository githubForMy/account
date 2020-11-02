package com.boniu.account.repository.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName AccountEntity
 * @Description 账户详细信息实体类
 * @Author HanXin
 * @Date 2019-07-11
 */

public class AccountEntity {
    //主键id
    private long id;

    //账户id
    private String accountId;

    //APP渠道
    private String appName;

    //注册手机号码
    private String mobile;

    //用户名/登录名
    private String userName;

    //邮箱地址
    private String email;

    //登录密码
    private String password;

    //昵称
    private String nickName;

    //头像地址
    private String headImg;

    //性别
    private String sexual;

    //生日
    private Date birthday;

    //个性签名
    private String autograph;

    //邀请码
    private String inviteCode;

    //邀请人账户id
    private String inviteAccountId;

    //游客使用，未注册情况下用户唯一识别码
    private String uuid;

    //注册时间
    private Date registerTime;

    //账户类型
    private String type;

    //账户状态
    private String status;

    /**
     * 是否开启自动续费状态，参考{@link com.boniu.base.utile.enums.BooleanEnum}
     */
    private String autoPay;

    //会员到期时间
    private Date vipExpireTime;

    //注册渠道
    private String channel;

    //游客注册渠道
    private String visitorChannel;

    //登录秘钥
    private String token;

    //登录秘钥过期时间
    private Date tokenExpireTime;

    //最后一次登录时间
    private Date lastLoginTime;

    //最后一次登录IP地址
    private String lastLoginIp;

    //微信唯一标识
    private String openId;

    //微信开发平台-全平台唯一标识
    private String unionid;

    //备注信息
    private String content;

    //设备品牌
    private String brand;

    //设备型号
    private String deviceModel;

    //设备最后一次登录所属平台
    private String platform;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    private Date applyCancelTime;   //申请注销时间
    private Date finishCancelTime;  //完成注销时间

    //可使用的体验次数-不通用，某些APP可用
    private Integer times;

    private Date registerStartTime;

    private Date registerEndTime;

    private Date lastLoginStartTime;

    private Date lastLoginEndTime;

    private Integer page;

    private Integer size;

    private String accountIdIsNull;

    //APP名称
    private String appTitle;

    public Date getRegisterStartTime() { return registerStartTime; }

    public void setRegisterStartTime(Date registerStartTime) { this.registerStartTime = registerStartTime; }

    public Date getRegisterEndTime() { return registerEndTime; }

    public void setRegisterEndTime(Date registerEndTime) { this.registerEndTime = registerEndTime; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVisitorChannel() {
        return visitorChannel;
    }

    public void setVisitorChannel(String visitorChannel) {
        this.visitorChannel = visitorChannel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getApplyCancelTime() {
        return applyCancelTime;
    }

    public void setApplyCancelTime(Date applyCancelTime) {
        this.applyCancelTime = applyCancelTime;
    }

    public Date getFinishCancelTime() {
        return finishCancelTime;
    }

    public void setFinishCancelTime(Date finishCancelTime) {
        this.finishCancelTime = finishCancelTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getLastLoginStartTime() {
        return lastLoginStartTime;
    }

    public void setLastLoginStartTime(Date lastLoginStartTime) {
        this.lastLoginStartTime = lastLoginStartTime;
    }

    public Date getLastLoginEndTime() {
        return lastLoginEndTime;
    }

    public void setLastLoginEndTime(Date lastLoginEndTime) {
        this.lastLoginEndTime = lastLoginEndTime;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAccountIdIsNull() {
        return accountIdIsNull;
    }

    public void setAccountIdIsNull(String accountIdIsNull) {
        this.accountIdIsNull = accountIdIsNull;
    }

    public String getAppTitle() { return appTitle; }

    public void setAppTitle(String appTitle) { this.appTitle = appTitle; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
