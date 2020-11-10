package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * @ClassName QueryAccountByAppTitleRequest
 * @Description
 * @Author FengXian
 * @Date 2020-10-11
 */

@ApiModel("管理后台通过appName和platform查询用户信息入参")
public class QueryAccountByAppTitleRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = false)
    private String appName;
    @ApiModelProperty(value = "APP名称", example = "童话故事", required = false)
    private String platform;

    public String getAppName() { return appName; }

    public void setAppName(String appName) { this.appName = appName; }

    public String getPlatform() { return platform; }

    public void setPlatform(String platform) { this.platform = platform; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
