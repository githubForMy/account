package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description
 * @Author hanxin
 * @Date 2020/12/7
 */

public class AccountVipGroupVo {
    @ApiModelProperty(value = "权益组别（TYPE_ONE、TYPE_TWO、TYPE_xxx 需要和产品人员定义）", example = "", required = false)
    private String groupType;
    @ApiModelProperty(value = "会员类型（VIP、FOREVER_VIP）", example = "", required = false)
    private String vipType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
