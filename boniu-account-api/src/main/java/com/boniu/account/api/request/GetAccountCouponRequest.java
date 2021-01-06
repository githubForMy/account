package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 获取用户优惠券信息入参
 * @Author hanxin
 * @Date 2021/1/4
 */
@ApiModel("获取用户优惠券信息入参")
public class GetAccountCouponRequest extends BaseRequest {
    @ApiModelProperty(value = "用户优惠券编号", example = "", required = false)
    private String accountCouponId;
    @ApiModelProperty(value = "优惠券编号", example = "", required = false)
    private String couponId;
    @ApiModelProperty(value = "优惠券类型", example = "", required = false)
    private String type;
    @ApiModelProperty(value = "优惠券状态", example = "", required = false)
    private String status;
    @ApiModelProperty(value = "优惠券来源", example = "", required = false)
    private String source;

    public String getAccountCouponId() {
        return accountCouponId;
    }

    public void setAccountCouponId(String accountCouponId) {
        this.accountCouponId = accountCouponId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
