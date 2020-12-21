package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @description: 推送目标返参
 * @author: FengXian
 * @create: 2020-12-18 16:49
 **/
@ApiModel("推送目标返参")
public class AccountPushInfoVO {
    @ApiModelProperty(value = "uuid", example = "101590082981739", required = true)
    private String uuid;
    @ApiModelProperty(value = "平台", example = "IOS", required = true)
    private String platform;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
