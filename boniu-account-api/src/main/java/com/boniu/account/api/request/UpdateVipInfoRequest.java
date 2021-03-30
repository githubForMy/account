package com.boniu.account.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @Description 更新用户会员信息入参实体
 * @Author FengXian
 * @Date 2021-01-29
 */
@ApiModel("更新用户会员信息入参实体")
public class UpdateVipInfoRequest {
    @ApiModelProperty(value = "唯一编号", example = "1234791873900", required = true)
    private String accountVipId;
    @ApiModelProperty(value = "状态", example = "NORMAL", required = false)
    private String status;
    @ApiModelProperty(value = "是否当前使用中", example = "VIP", required = false)
    private String isUseing;
    @ApiModelProperty(value = "是否为自动订阅会员", example = "YES", required = false)
    private String autoPay;
    @ApiModelProperty(value = "会员有效期", required = false)
    private Date expireTime;
    @ApiModelProperty(value = "使用次数", required = false)
    private Integer limitTimes;
    @ApiModelProperty(value = "会员时长", required = false)
    private Integer limitTimeLength;

    public String getAccountVipId() {
        return accountVipId;
    }

    public void setAccountVipId(String accountVipId) {
        this.accountVipId = accountVipId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsUseing() {
        return isUseing;
    }

    public void setIsUseing(String isUseing) {
        this.isUseing = isUseing;
    }

    public String getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(String autoPay) {
        this.autoPay = autoPay;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getLimitTimes() {
        return limitTimes;
    }

    public void setLimitTimes(Integer limitTimes) {
        this.limitTimes = limitTimes;
    }

    public Integer getLimitTimeLength() {
        return limitTimeLength;
    }

    public void setLimitTimeLength(Integer limitTimeLength) {
        this.limitTimeLength = limitTimeLength;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
