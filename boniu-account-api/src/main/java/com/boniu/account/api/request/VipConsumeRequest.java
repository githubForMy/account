package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 会员权益消耗入参
 * @Author hanxin
 * @Date 2020/11/23
 */

@ApiModel("会员权益消耗入参")
public class VipConsumeRequest extends BaseRequest {
    @ApiModelProperty(value = "使用时长（分钟）", example = "1", required = false)
    private Integer timeLength;
    @ApiModelProperty(value = "使用次数", example = "1", required = false)
    private Integer times;

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
