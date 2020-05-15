package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName AddUuidRequest
 * @Description
 * @Author HanXin
 * @Date 2020-02-26
 */

@ApiModel("新增设备信息入参")
public class AddUuidRequest {
    @ApiModelProperty(value = "设备编号", example = "BDAF6B4D-5DC0-4AEF-BCF8-6C7EFC94DE97", required = true)
    private String uuid;
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;
    @ApiModelProperty(value = "设备平台（苹果：IOS；安卓：ANDROID；）", example = "IOS", required = true)
    private String platform;
    @ApiModelProperty(value = "设备品牌", example = "huawei", required = false)
    private String brand;
    @ApiModelProperty(value = "设备型号", example = "XIAOMI MIX2", required = false)
    private String deviceModel;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
