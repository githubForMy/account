package com.boniu.account.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * @ClassName QueryAccountListRequest
 * @Description
 * @Author HanXin
 * @Date 2020-02-26
 */

@ApiModel("通过条件查询账户列表信息")
public class QueryAccountListRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;

    @ApiModelProperty(value = "用户账户id", example = "10034982983792", required = false)
    private String accountId;

    @ApiModelProperty(value = "注册手机号", example = "18888888888", required = false)
    private String mobile;

    @ApiModelProperty(value = "会员类型", example = "VIP", required = false)
    private String type;

    @ApiModelProperty(value = "注册渠道", example = "AppStore", required = false)
    private String channel;

    @ApiModelProperty(value = "用户状态", example = "NORMAL-正常, FREEZE-账户已冻结, CANCEL-注销", required = false)
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "注册起期", example = "2019-04-07 00:00:00", required = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "注册止期", example = "2019-04-07 00:00:00", required = false)
    private Date endTime;


    public Date getStartTime() { return startTime; }

    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }

    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
