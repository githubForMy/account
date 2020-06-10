package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountMainEntity;

/**
 * @InterfaceName AccountMainMapper
 * @Author HanXin
 * @Date 2019-07-11
 */

public interface AccountMainMapper {

    /**
     * 通过条件查询
     *
     * @param mobile
     * @return
     */
    AccountMainEntity selectBy(AccountMainEntity entity);

    /**
     * 插入总账户数据
     *
     * @param entity
     * @return
     */
    int saveAccountMain(AccountMainEntity entity);

    /**
     * 更新总账户数据
     *
     * @param entity
     * @return
     */
    int updateAccountMain(AccountMainEntity entity);
}
