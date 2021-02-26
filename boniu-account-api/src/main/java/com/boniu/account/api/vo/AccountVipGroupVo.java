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
    /*@ApiModelProperty(value = "会员到期时间", example = "2019-04-07 00:00:00", required = false)
    private Date vipExpireTime;
    @ApiModelProperty(value = "会员有效时间", example = "31", required = false)
    private Integer vipExpireDays;
    @ApiModelProperty(value = "功能可消耗次数", example = "3", required = false)
    private Integer times;*/
    @ApiModelProperty(value = "是否订阅", example = "", required = false)
    private String autoPay;

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

    /*public Date getVipExpireTime() { return vipExpireTime; }

    public void setVipExpireTime(Date vipExpireTime) { this.vipExpireTime = vipExpireTime; }

    public Integer getVipExpireDays() { return vipExpireDays; }

    public void setVipExpireDays(Integer vipExpireDays) { this.vipExpireDays = vipExpireDays; }

    public Integer getTimes() { return times; }

    public void setTimes(Integer times) { this.times = times; }*/

    public String getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(String autoPay) {
        this.autoPay = autoPay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
