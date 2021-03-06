package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName LoginAccountRequest
 * @Author HanXin
 * @Date 2019-07-02
 */

@ApiModel("账户登录入参")
public class LoginAccountRequest {
    @ApiModelProperty(value = "注册手机号（accountType值为NORMAL情况下必传）", example = "18888888888", required = false)
    private String mobile;
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;
    @ApiModelProperty(value = "注册渠道（accountType值为NORMAL情况下必传）", example = "web", required = false)
    private String channel;
    @ApiModelProperty(value = "游客注册渠道（accountType值为VISITOR情况下必传）", example = "visitor_web", required = false)
    private String visitorChannel;
    @ApiModelProperty(value = "设备唯一识别号", example = "110123456", required = true)
    private String uuid;
    @ApiModelProperty(value = "账户ID（accountType值为NORMAL情况下必传）", example = "", required = false)
    private String accountId;
    @ApiModelProperty(value = "账户类型，游客-VISITOR,用户-NORMAL", example = "VISITOR", required = true)
    private String accountType;
    @ApiModelProperty(value = "设备品牌", example = "XIAOMI", required = false)
    private String brand;
    @ApiModelProperty(value = "设备型号", example = "MIX2", required = false)
    private String deviceModel;
    @ApiModelProperty(value = "设备所在网络环境的ip地址", example = "192.168.10.110", required = true)
    private String ip;
    @ApiModelProperty(value = "用户头像(新用户默认头像写入)", example = "https://boniuapp.rhinox.cn/common/head/1578303552334.png", required = true)
    private String headImg;
    @ApiModelProperty(value = "设备类型", example = "IOS/ANDROID", required = true)
    private String deviceType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
