package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName VisitorAccountVO
 * @Author HanXin
 * @Date 2019-10-09
 */

@ApiModel("游客账户基本信息")
public class VisitorAccountVO {
    @ApiModelProperty(value = "用户ID(登陆成功时返回),2小时有效",example = "kmWnq/zMgK6MwBcO+hEQqKAnwLcZev0VCchlRUrsY0w=")
    private String accountId;
    @ApiModelProperty(value = "2个月有效token,可以用于换取新的加密后的账户ID",example = "evq8xlstngmpb0zxr0hwq4fpfwxqmypb")
    private String token;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
