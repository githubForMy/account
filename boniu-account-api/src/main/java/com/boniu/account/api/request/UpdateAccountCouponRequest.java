package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author hanxin
 * @Date 2021/1/4
 */

@ApiModel("更新用户优惠券信息入参")
public class UpdateAccountCouponRequest extends BaseRequest {
    @ApiModelProperty(value = "用户优惠券编号", example = "", required = false)
    private String accountCouponId;
    @ApiModelProperty(value = "优惠券状态", example = "", required = false)
    private String status;

    public String getAccountCouponId() {
        return accountCouponId;
    }

    public void setAccountCouponId(String accountCouponId) {
        this.accountCouponId = accountCouponId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
