package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountOpenPartnerEntity;

public interface AccountOpenPartnerMapper {

    /**
     * 根据条件查询
     *
     * @param entity
     * @return
     */
    AccountOpenPartnerEntity selectBy(AccountOpenPartnerEntity entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(AccountOpenPartnerEntity entity);

    /**
     * 保存数据
     *
     * @param entity
     * @return
     */
    int save(AccountOpenPartnerEntity entity);

}