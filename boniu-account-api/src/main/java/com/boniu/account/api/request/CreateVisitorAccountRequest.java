package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName CreateVisitorAccountRequest
 * @Author HanXin
 * @Date 2019-09-03
 */

@ApiModel("创建游客账户入参")
public class CreateVisitorAccountRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY")
    private String appName;

    @ApiModelProperty(value = "设备唯一标识码,由客户端生成并保存,终端唯一", example = "100123456")
    private String deviceId;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
