package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName AccountCancelVO
 * @Description 账户注销返回信息
 * @Author HanXin
 * @Date 2020-01-06
 */

@ApiModel("账户注销返回信息")
public class AccountCancelVO {
    @ApiModelProperty(value = "手机号码，客户端脱敏", example = "18888888888")
    private String mobile;

    @ApiModelProperty(value = "申请时间", example = "2020-01-01 12：00：00")
    private Date applyTime;

    @ApiModelProperty(value = "账户注销审核所需时间", example = "7")
    private int day;

    @ApiModelProperty(value = "预计审核完成时间", example = "2020-01-01 12：00：00")
    private Date finishTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
