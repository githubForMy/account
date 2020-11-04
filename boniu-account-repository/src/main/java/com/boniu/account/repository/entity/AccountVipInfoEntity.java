package com.boniu.account.repository.entity;

import java.util.Date;

/**
 * @Description 账户会员信息实体类
 * @Author hanxin
 * @Date 2020/10/28
 */

public class AccountVipInfoEntity {
    private Long id;
    private String accountVipId;        //账户会员信息编号
    private String accountId;           //账户编号
    private String appName;             //app标识
    private String uuid;                //设备信息
    private String vipType;             //会员类型
    private Date expireTime;          //会员有效期
    private String isForever;           //是否永久会员
    private Integer limitTimes;          //剩余使用次数
    private Integer limitTimeLength;     //剩余使用时长
    private String status;                 //状态
    private String isUseing;               //是否当前使用中
    private Date createTime;
    private Date updateTime;

    private String accountIdNull;           //accountId为空

    private Boolean isExpireTimeExist;          //是否存在有效期类型
    private Boolean isLimitTimesExist;          //是否存在按次数类型
    private Boolean isLimitTimeLengthExist;     //是否存在按时长类型
    private Boolean isForeverExist;             //是否永久类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountVipId() {
        return accountVipId;
    }

    public void setAccountVipId(String accountVipId) {
        this.accountVipId = accountVipId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getIsForever() {
        return isForever;
    }

    public void setIsForever(String isForever) {
        this.isForever = isForever;
    }

    public Integer getLimitTimes() {
        return limitTimes;
    }

    public void setLimitTimes(Integer limitTimes) {
        this.limitTimes = limitTimes;
    }

    public Integer getLimitTimeLength() {
        return limitTimeLength;
    }

    public void setLimitTimeLength(Integer limitTimeLength) {
        this.limitTimeLength = limitTimeLength;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsUseing() {
        return isUseing;
    }

    public void setIsUseing(String isUseing) {
        this.isUseing = isUseing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccountIdNull() {
        return accountIdNull;
    }

    public void setAccountIdNull(String accountIdNull) {
        this.accountIdNull = accountIdNull;
    }

    public Boolean getExpireTimeExist() {
        return isExpireTimeExist;
    }

    public void setExpireTimeExist(Boolean expireTimeExist) {
        isExpireTimeExist = expireTimeExist;
    }

    public Boolean getLimitTimesExist() {
        return isLimitTimesExist;
    }

    public void setLimitTimesExist(Boolean limitTimesExist) {
        isLimitTimesExist = limitTimesExist;
    }

    public Boolean getLimitTimeLengthExist() {
        return isLimitTimeLengthExist;
    }

    public void setLimitTimeLengthExist(Boolean limitTimeLengthExist) {
        isLimitTimeLengthExist = limitTimeLengthExist;
    }

    public Boolean getForeverExist() {
        return isForeverExist;
    }

    public void setForeverExist(Boolean foreverExist) {
        isForeverExist = foreverExist;
    }
}
