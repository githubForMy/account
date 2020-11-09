package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountCancelApplyEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author hanxin
 * @Date 2020/10/29
 */

public interface AccountCancelApplyMapper {
    /**
     * 通过账户编号查询
     * @param accountId
     * @return
     */
    AccountCancelApplyEntity selectByAccountId(@Param("accountId") String accountId);

    /**
     * 保存数据
     * @param entity
     * @return
     */
    int save(AccountCancelApplyEntity entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(AccountCancelApplyEntity entity);
}
