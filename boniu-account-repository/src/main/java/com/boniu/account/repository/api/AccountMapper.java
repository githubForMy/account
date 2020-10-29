package com.boniu.account.repository.api;

import com.boniu.account.repository.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 更新账户数据信息
     *
     * @param entity
     * @return
     */
    int updateAccountById(AccountEntity entity);

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

    /**
     * 通过token查询用户
     *
     * @param token
     * @return
     */
    AccountEntity selectByToken(@Param("token") String token);

    /**
     * 通过uuid查询游客用户，仅仅查询出手机号码为空的数据
     * @param uuid
     * @return
     */
    AccountEntity selectByUuid(@Param("uuid") String uuid, @Param("appName") String appName, @Param("accountIdIsNull") String accountIdIsNull);

    /**
     * 通过参数查询
     * @param entity
     * @return
     */
    List<AccountEntity> selectListBy(AccountEntity entity);

    /**
     * 通过参数查询总数量
     *
     * @param entity
     * @return
     */
    int selectListCountBy(AccountEntity entity);

    /**
     * 通过username查询账户
     * @param userName
     * @return
     */
    AccountEntity selectByUserName(@Param("userName") String userName);

    /**
     * 通过username和password查询账户
     *
     * @param userName
     * @param password
     * @return
     */
    AccountEntity selectByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据username重设密码
     *
     * @param entity
     * @return
     */
    int resetPassword(AccountEntity entity);

    /**
     * 根据邀请码查询账户信息
     *
     * @param inviteCode
     * @return
     */
    AccountEntity selecyByInviteCode(@Param("inviteCode") String inviteCode);

    /**
     * 查询会员已过期的账户信息
     *
     * @return
     */
    List<AccountEntity> selectExpireAccountList();

    /**
     * 根据账户编号合集更新账户会员状态
     *
     * @param accountIds
     * @param type
     * @return
     */
    int updateTypeByAccountIds(@Param("accountIds") List<String> accountIds, @Param("type") String type);

    /**
     * 根据账户编号合集更新账户注销申请时间
     *
     * @param accountIds
     * @return
     */
    int updateApplyCancelTimeByAccountIds(@Param("accountIds") List<String> accountIds);

    /**
     * 查询申请注销的账户信息
     *
     * @return
     */
    List<AccountEntity> selectCancelAccountList();

}
