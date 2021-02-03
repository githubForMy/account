package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * @ClassName QueryAccountListForAdminRequest
 * @Description
 * @Author FengXian
 * @Date 2020-11-09
 */

@ApiModel("管理后台通过参数查询用户信息")
public class QueryAccountListForAdminRequest {
    @ApiModelProperty(value = "关联信息", example = "appName+platform", required = false)
    private List<QueryAccountByAppTitleRequest> list;
    @ApiModelProperty(value = "用户账户id", example = "10034982983792", required = false)
    private String accountId;
    @ApiModelProperty(value = "注册手机号", example = "18888888888", required = false)
    private String mobile;
    @ApiModelProperty(value = "会员类型", example = "VIP", required = false)
    private String type;
    @ApiModelProperty(value = "注册渠道", example = "AppStore", required = false)
    private String channel;
    @ApiModelProperty(value = "平台", example = "IOS", required = false)
    private String platform;
    @ApiModelProperty(value = "设备号", example = "1212", required = false)
    private String uuid;
    @ApiModelProperty(value = "用户状态", example = "NORMAL-正常, FREEZE-账户已冻结, CANCEL-注销", required = false)
    private String status;
    @ApiModelProperty(value = "查第几页", example = "1", required = true)
    private Integer requestPage = 1;
    @ApiModelProperty(value = "每页展现的数量", example = "40", required = true)
    private Integer pageSize = 40;

    public List<QueryAccountByAppTitleRequest> getList() {
        return list;
    }

    public void setList(List<QueryAccountByAppTitleRequest> list) {
        this.list = list;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setStatus(String status) { this.status = status; }

    public Integer getRequestPage() { return requestPage; }

    public void setRequestPage(Integer requestPage) { this.requestPage = requestPage; }

    public Integer getPageSize() { return pageSize; }

    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
