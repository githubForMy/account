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
public class CancelAccountAutoVipInfoRequest extends BaseRequest {
    @ApiModelProperty(value = "分组", example = "TYPE_ONE", required = true)
    private String groupType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
