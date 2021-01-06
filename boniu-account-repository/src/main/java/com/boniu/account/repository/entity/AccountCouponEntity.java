package com.boniu.account.repository.entity;

import java.util.Date;

/**
 * @Description 用户券信息表实体类
 * @Author hanxin
 * @Date 2021/01/04
 */
public class AccountCouponEntity {

    //主键编号
    private Long id;

    //用户券编号
    private String accountCouponId;

    //优惠券编号
    private String couponId;

    //账户编号
    private String accountId;

    //券类型
    private String type;

    //券金额
    private Integer amount;

    //券使用状态
    private String status;

    //券来源
    private String source;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCouponId() {
        return accountCouponId;
    }

    public void setAccountCouponId(String accountCouponId) {
        this.accountCouponId = accountCouponId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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