package com.boniu.account.api.request;

import com.boniu.base.utile.message.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @ClassName UpdateAccountRequest
 * @Author HanXin
 * @Date 2019-07-15
 */

@ApiModel("更新账户信息入参入参")
public class UpdateAccountRequest extends BaseRequest {
    @ApiModelProperty(value = "用户昵称", example = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像链接地址", example = "http://boniustory.oss-cn-hangzhou.aliyuncs.com/operate/20190328/1553754539089.jpg")
    private String headImg;

    @ApiModelProperty(value = "性别", example = "MALE")
    private String sexual;

    @ApiModelProperty(value = "宝宝生日", example = "2019-04-07")
    private Date birthday;

    @ApiModelProperty(value = "个性签名", example = "签名内容")
    private String autograph;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
