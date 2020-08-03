package com.boniu.account.repository.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @InterfaceName AccountMainEntity
 * @Description 主账户实体类
 * @Author HanXin
 * @Date 2019-07-11
 */

public class AccountMainEntity {
    private Long id;

    private String accountMainId;

    private String mobile;

    private BigDecimal totalRevenue;

    private BigDecimal remainBalance;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountMainId() {
        return accountMainId;
    }

    public void setAccountMainId(String accountMainId) {
        this.accountMainId = accountMainId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getRemainBalance() {
        return remainBalance;
    }

    public void setRemainBalance(BigDecimal remainBalance) {
        this.remainBalance = remainBalance;
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
}
