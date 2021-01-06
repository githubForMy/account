package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 新增用户优惠券记录入参
 * @Author hanxin
 * @Date 2021/1/4
 */
@ApiModel("新增用户优惠券记录入参")
public class AddAccountCouponRequest extends BaseRequest {
    @ApiModelProperty(value = "优惠券编号", example = "", required = true)
    private String couponId;
    @ApiModelProperty(value = "优惠券类型", example = "", required = true)
    private String type;
    @ApiModelProperty(value = "优惠券金额", example = "3000", required = true)
    private Integer amount;
    @ApiModelProperty(value = "优惠券来源", example = "", required = true)
    private String source;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
