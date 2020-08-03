package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountMainEntity;

/**
 * @InterfaceName AccountMainMapper
 * @Author HanXin
 * @Date 2019-07-11
 */

public interface AccountMainMapper {
    /**
     * 根据条件查询
     * @param entity
     * @return
     */
    AccountMainEntity selectBy(AccountMainEntity entity);

    /**
     * 保存数据
     * @param entity
     * @return
     */
    int save(AccountMainEntity entity);

    /**
     * 保存数据
     * @param entity
     * @return
     */
    int update(AccountMainEntity entity);
}
