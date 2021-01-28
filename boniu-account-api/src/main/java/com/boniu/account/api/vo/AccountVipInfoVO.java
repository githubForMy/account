package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName AccountDetailVO
 * @Description 账户详细信息
 * @Author HanXin
 * @Date 2019-07-12
 */

@ApiModel("账户详细信息")
public class AccountVipInfoVO {
    @ApiModelProperty(value = "账户id", example = "10155970543370710044", required = true)
    private String accountId;
    @ApiModelProperty(value = "APP渠道", example = "STORY", required = true)
    private String appName;
    @ApiModelProperty(value = "设备号", example = "188888888888", required = true)
    private String uuid;
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String vipType;
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private Date expireTime;          //会员有效期
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String isForever;           //是否永久会员
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private Integer limitTimes;          //剩余使用次数
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private Integer limitTimeLength;     //剩余使用时长
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String status;                 //状态
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String isUseing;               //是否当前使用中
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String productGroup;            //所属产品组别
    @ApiModelProperty(value = "会员类型", example = "VIP", required = true)
    private String autoPay;             //是否为自动订阅会员

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getIsForever() {
        return isForever;
    }

    public void setIsForever(String isForever) {
        this.isForever = isForever;
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

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

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
