package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountMainOpenEntity;

/**
 * @InterfaceName AccountMainOpenMapper
 * @Description
 * @Author HanXin
 * @Date 2020-06-11
 */

public interface AccountMainOpenMapper {
    /**
     * 根据条件查询
     *
     * @param entity
     * @return
     */
    AccountMainOpenEntity selectBy(AccountMainOpenEntity entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(AccountMainOpenEntity entity);

    /**
     * 保存数据
     *
     * @param entity
     * @return
     */
    int save(AccountMainOpenEntity entity);
}
