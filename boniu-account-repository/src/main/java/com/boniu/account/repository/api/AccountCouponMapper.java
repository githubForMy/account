package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountCouponEntity;

/**
 * @Description
 * @Author hanxin
 * @Date 2020/10/29
 */
public interface AccountCouponMapper {
    /**
     * 保存数据
     * @param entity
     * @return
     */
    int save(AccountCouponEntity entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(AccountCouponEntity entity);

    /**
     * 查询数据
     * @param entity
     * @return
     */
    AccountCouponEntity selectBy(AccountCouponEntity entity);
}