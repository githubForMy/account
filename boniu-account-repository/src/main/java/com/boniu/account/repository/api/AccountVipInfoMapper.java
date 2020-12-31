package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountVipInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author hanxin
 * @Date 2020/10/28
 */

public interface AccountVipInfoMapper {
    /**
     * 插入会员信息
     * @param entity
     * @return
     */
    int saveVipInfo(AccountVipInfoEntity entity);

    /**
     * 通过参数获取会员信息列表
     * @param entity
     * @return
     */
    List<AccountVipInfoEntity> getVipInfoBy(AccountVipInfoEntity entity);

    /**
     * 通过会员编号获取会员信息列表
     * @param accountId
     * @return
     */
    List<AccountVipInfoEntity> getVipInfoByAccountId(@Param("accountId") String accountId);

    /**
     * 通过会员权益编号获取会员权益
     * @param accountVipId
     * @return
     */
    AccountVipInfoEntity getVipInfoByAccountVipId(@Param("accountVipId") String accountVipId);

    /**
     * 更新会员权益
     *
     * @param entity
     * @return
     */
    int updateVipInfo(AccountVipInfoEntity entity);

    /**
     * 取消用户会员
     *
     * @param accountId
     * @return
     */
    int cancelVipInfo(@Param("accountId") String accountId);
}
