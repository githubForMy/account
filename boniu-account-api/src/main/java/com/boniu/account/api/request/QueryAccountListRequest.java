package com.boniu.account.api.request;

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

@ApiModel("通过条件分页查询账户列表信息入参")
public class QueryAccountListRequest {
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = false)
    private String appName;
    @ApiModelProperty(value = "APP名称", example = "童话故事", required = false)
    private String appTitle;
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
    @ApiModelProperty(value = "注册起期", example = "2019-04-07 00:00:00", required = false)
    private Date registerStartTime;
    @ApiModelProperty(value = "注册止期", example = "2019-04-07 00:00:00", required = false)
    private Date registerEndTime;
    @ApiModelProperty(value = "最后登录时间起期", example = "2019-04-07 00:00:00", required = false)
    private Date lastLoginStartTime;
    @ApiModelProperty(value = "最后登录时间止期", example = "2019-04-07 00:00:00", required = false)
    private Date lastLoginEndTime;
    @ApiModelProperty(value = "邀请人账户编号", example = "10034982983792", required = false)
    private String inviteAccountId;
    @ApiModelProperty(value = "查第几页", example = "1", required = true)
    private Integer requestPage = 1;
    @ApiModelProperty(value = "每页展现的数量", example = "40", required = true)
    private Integer pageSize = 40;

    public String getAppTitle() { return appTitle; }

    public void setAppTitle(String appTitle) { this.appTitle = appTitle; }

    public Date getRegisterStartTime() {
        return registerStartTime;
    }

    public void setRegisterStartTime(Date registerStartTime) {
        this.registerStartTime = registerStartTime;
    }

    public Date getRegisterEndTime() {
        return registerEndTime;
    }

    public void setRegisterEndTime(Date registerEndTime) {
        this.registerEndTime = registerEndTime;
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

    public String getInviteAccountId() {
        return inviteAccountId;
    }

    public void setInviteAccountId(String inviteAccountId) {
        this.inviteAccountId = inviteAccountId;
    }

    public Integer getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(Integer requestPage) {
        this.requestPage = requestPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
