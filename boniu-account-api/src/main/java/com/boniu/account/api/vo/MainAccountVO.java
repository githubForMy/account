package com.boniu.account.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName MainAccountVO
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */
@ApiModel("主账户信息返回结果")
public class MainAccountVO {
    @ApiModelProperty(value = "主账户编号", example = "101590082981739", required = true)
    private String mainAccountId;
    @ApiModelProperty(value = "手机号码", example = "12222222222", required = true)
    private String mobile;

    public String getMainAccountId() {
        return mainAccountId;
    }

    public void setMainAccountId(String mainAccountId) {
        this.mainAccountId = mainAccountId;
    }

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
