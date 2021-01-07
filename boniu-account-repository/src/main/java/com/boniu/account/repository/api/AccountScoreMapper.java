package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountScoreEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: FengXian
 * @create: 2021-01-06 10:18
 **/
public interface AccountScoreMapper {

    /**
     * 保存数据
     *
     * @param entity
     * @return
     */
    int save(AccountScoreEntity entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(AccountScoreEntity entity);

    /**
     * 通过accountId查询
     *
     * @param accountId
     * @return
     */
    AccountScoreEntity selectByAccountId(@Param("accountId") String accountId);
}
