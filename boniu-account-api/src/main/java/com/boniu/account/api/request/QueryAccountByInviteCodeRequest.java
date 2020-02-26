package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName QueryAccountByInviteCodeRequest
 * @Author HanXin
 * @Date 2020-02-21
 */

@ApiModel("通过邀请码查询账户信息入参")
public class QueryAccountByInviteCodeRequest {
    @ApiModelProperty(value = "账户邀请码", example = "jdv8sdvn", required = true)
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
