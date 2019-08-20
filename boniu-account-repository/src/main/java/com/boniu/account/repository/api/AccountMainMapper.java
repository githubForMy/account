package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountMainEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName AccountMainMapper
 * @Author HanXin
 * @Date 2019-07-11
 */

public interface AccountMainMapper {

    /**
     * 通过手机号码查询总账户
     *
     * @param mobile
     * @return
     */
    AccountMainEntity selectByMobile(@Param("mobile") String mobile);

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
