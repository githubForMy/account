package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by ZZF on 2019/10/16.
 */
@ApiModel("用户对象")
public class AccountVO {
    @ApiModelProperty(value = "账户ID(登陆成功时返回),2小时有效", example = "Hm8gVLHEM6gki4qMEopXz1VaiNE9896e9QdoDAg+H9I=", required = true)
    private String accountId;

    @ApiModelProperty(value = "2个月有效token,可以用于换取新的加密后的账户ID", example = "zy1o1yundbkucrcvri4o1ka0ebxdj2uy", required = true)
    private String token;

    @ApiModelProperty(value = "是否为新用户", example = "YES", required = false)
    private String isNew;

    @ApiModelProperty(value = "同步状态", example = "YES-同步成功，NO-同步失败", required = false)
    private String syncStatus;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
