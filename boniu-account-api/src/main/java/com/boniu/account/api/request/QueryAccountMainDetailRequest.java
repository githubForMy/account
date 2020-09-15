package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description 获取主账户详细信息入参
 * @Author HanXin
 * @Date 2020-06-08
 */

@ApiModel("查询主账户详细信息入参")
public class QueryAccountMainDetailRequest extends BaseRequest {
    @ApiModelProperty(value = "手机号码", example = "12222222222", required = false)
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
