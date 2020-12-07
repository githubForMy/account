package com.boniu.account.server.pojo;

import com.boniu.account.api.vo.AccountVipGroupVo;

import java.util.Date;
import java.util.List;

/**
 * Created by ZZF on 2020/6/14.
 */
public class AccountVipInfoPoJo {
    private String vipType;                 //会员类型
    private Date vipExpireTime;
    private Integer vipExpireDays;
    private Integer vipLimitTimes;
    private Integer vipLimitTimeLength;
    private List<AccountVipGroupVo> vipGroupInfos;

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Date getVipExpireTime() {
        return vipExpireTime;
    }

    public void setVipExpireTime(Date vipExpireTime) {
        this.vipExpireTime = vipExpireTime;
    }

    public Integer getVipExpireDays() {
        return vipExpireDays;
    }

    public void setVipExpireDays(Integer vipExpireDays) {
        this.vipExpireDays = vipExpireDays;
    }

    public Integer getVipLimitTimes() {
        return vipLimitTimes;
    }

    public void setVipLimitTimes(Integer vipLimitTimes) {
        this.vipLimitTimes = vipLimitTimes;
    }

    public Integer getVipLimitTimeLength() {
        return vipLimitTimeLength;
    }

    public void setVipLimitTimeLength(Integer vipLimitTimeLength) {
        this.vipLimitTimeLength = vipLimitTimeLength;
    }

    public List<AccountVipGroupVo> getVipGroupInfos() {
        return vipGroupInfos;
    }

    public void setVipGroupInfos(List<AccountVipGroupVo> vipGroupInfos) {
        this.vipGroupInfos = vipGroupInfos;
    }
}
