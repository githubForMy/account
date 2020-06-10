package com.boniu.account.repository.entity;

import java.util.Date;

/**
 * @InterfaceName AccountMainEntity
 * @Description 主账户实体类
 * @Author HanXin
 * @Date 2019-07-11
 */

public class AccountMainEntity {
    //主键id
    private Long id;

    //账户id
    private String mainAccountId;

    //注册手机号
    private String mobile;

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
