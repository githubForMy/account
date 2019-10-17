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
    @ApiModelProperty(value = "游客状态下生成的唯一用户识别号", example = "YK10155970945070710044")
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
