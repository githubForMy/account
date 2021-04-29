package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountCancelApplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     *
     * @param entity
     * @return
     */
    int update(AccountCancelApplyEntity entity);

    /**
     * 获取注销审核中的用户申请列表
     *
     * @return
     */
    List<AccountCancelApplyEntity> selectAuditList();

    /**
     * 根据账户编号合集更新账户注销状态为注销完成
     *
     * @param accountIds
     * @return
     */
    int updateCancelStatusByAccountIds(@Param("accountIds") List<String> accountIds);
}
