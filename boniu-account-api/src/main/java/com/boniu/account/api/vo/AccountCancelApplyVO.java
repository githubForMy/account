package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description 申请账户注销返回对象
 * @Author hanxin
 * @Date 2020/10/29
 */

@ApiModel("申请账户注销返回对象")
public class AccountCancelApplyVO {
    @ApiModelProperty(value = "手机号码", example = "18888888888", required = true)
    private String mobile;
    @ApiModelProperty(value = "弹框标识", example = "YES/NO", required = true)
    private String dialogState;
    @ApiModelProperty(value = "消耗天数", example = "7", required = true)
    private int consumeDays;
    @ApiModelProperty(value = "申请注销时间", example = "2020-01-01 00:00:00", required = true)
    private String applyTime;
    @ApiModelProperty(value = "预计注销完成", example = "2020-01-01 00:00:00", required = true)
    private String canncelTime;
    @ApiModelProperty(value = "注销状态", example = "INIT", required = true)
    private String state;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDialogState() {
        return dialogState;
    }

    public void setDialogState(String dialogState) {
        this.dialogState = dialogState;
    }

    public int getConsumeDays() {
        return consumeDays;
    }

    public void setConsumeDays(int consumeDays) {
        this.consumeDays = consumeDays;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getCanncelTime() {
        return canncelTime;
    }

    public void setCanncelTime(String canncelTime) {
        this.canncelTime = canncelTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
