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

@ApiModel("更新账户信息入参")
public class UpdateAccountRequest extends BaseRequest {
    @ApiModelProperty(value = "用户昵称", example = "昵称", required = false)
    private String nickname;

    @ApiModelProperty(value = "用户头像链接地址", example = "http://boniustory.oss-cn-hangzhou.aliyuncs.com/operate/20190328/1553754539089.jpg", required = false)
    private String headImg;

    @ApiModelProperty(value = "性别", example = "MALE", required = false)
    private String sexual;

    @ApiModelProperty(value = "宝宝生日", example = "2019-04-07", required = false)
    private String birthday;

    @ApiModelProperty(value = "个性签名", example = "签名内容", required = false)
    private String autograph;

    @ApiModelProperty(value = "会员过期时间", example = "2019-04-07 00:00:00", required = false)
    private Date vipExpireTime;

    @ApiModelProperty(value = "账户类型", example = "NORMAL-普通用户，VIP-会员用户", required = false)
    private String type;

    @ApiModelProperty(value = "账户状态", example = "NORMAL-普通用户，FREEZE-注销", required = false)
    private String status;

    @ApiModelProperty(value = "是否是自动订阅", example = "YES", required = false)
    private String autoPay;

    @ApiModelProperty(value = "可消耗的功能使用次数", example = "20", required = false)
    private Integer times;

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Date getVipExpireTime() {
        return vipExpireTime;
    }

    public void setVipExpireTime(Date vipExpireTime) {
        this.vipExpireTime = vipExpireTime;
    }

    public String getAutoPay() { return autoPay; }

    public void setAutoPay(String autoPay) { this.autoPay = autoPay; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
