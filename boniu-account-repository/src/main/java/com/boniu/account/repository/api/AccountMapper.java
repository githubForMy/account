package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName AccountMapper
 * @Author HanXin
 * @Date 2019-07-11
 */

public interface AccountMapper {
    /**
     * 通过手机号和APP渠道查询用户
     *
     * @param mobile
     * @param appName
     * @return
     */
    AccountEntity selectByMobileAndAppName(@Param("mobile") String mobile, @Param("appName") String appName);

    /**
     * 通过邀请码查询是否已存在邀请码
     *
     * @param inviteCode
     * @return
     */
    int countByInviteCode(@Param("inviteCode") String inviteCode);

    /**
     * 插入账户数据信息
     *
     * @param entity
     * @return
     */
    int saveAccount(AccountEntity entity);

    /**
     * 更新账户数据信息
     *
     * @param entity
     * @return
     */
    int updateAccount(AccountEntity entity);

    /**
     * 通过账户id和APP渠道查询用户
     *
     * @param accountId
     * @param appName
     * @return
     */
    AccountEntity selectByAccountIdAndAppName(@Param("accountId") String accountId, @Param("appName") String appName);

    /**
     * 通过token和注册手机号查询用户
     *
     * @param token
     * @param mobile
     * @return
     */
    AccountEntity selectByTokenAndMobile(@Param("token") String token, @Param("mobile") String mobile);
}
