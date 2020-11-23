package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 更新用户会员信息入参
 * @Author hanxin
 * @Date 2020/11/23
 */
@ApiModel("更新用户会员信息入参")
public class UpdateAccountVipInfoRequest extends BaseRequest {
    @ApiModelProperty(value = "支付订单编号", example = "1234791873900", required = true)
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
