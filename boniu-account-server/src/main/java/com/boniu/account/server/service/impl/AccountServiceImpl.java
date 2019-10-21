package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountStatusEnum;
import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.enums.AccountVipTypeEnum;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.repository.api.AccountMapper;
import com.boniu.account.repository.entity.AccountEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.exception.ErrorEnum;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AccountServiceImpl
 * @Description 账户相关接口实现类
 * @Author HanXin
 * @Date 2019-07-02
 */

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Resource
//    private AccountMainMapper accountMainMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 验证账户是否已注册
     *
     * @param request
     * @return
     */
    @Override
    public Boolean checkAccount(CheckAccountRequest request) {
        //查询用户是否存在
        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());

        if (null == accountEntity) {
            return false;
        }
        return true;
    }

    /**
     * 注册新账户
     *
     * @param request
     * @return
     */
    @Override
    public Boolean registerAccount(RegisterAccountRequest request) {
//        //查询总账户是否存在
//        AccountMainEntity accountMainEntity = accountMainMapper.selectByMobile(request.getMobile());
//
//        //如果总账户不存在，则新建，若存在则更新数据
//        if (null == accountMainEntity) {
//            //插入新数据
//            accountMainEntity = new AccountMainEntity();
//            accountMainEntity.setAccountId(IDUtils.createID());
//            accountMainEntity.setMobile(request.getMobile());
//            accountMainEntity.setCreateTime(new Date());
//            int num = accountMainMapper.saveAccountMain(accountMainEntity);
//            if (num != 1) {
//                logger.error("#1[注册新账户]-[插入APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
//                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
//            }
//        } else {
//            //更新总账户数据信息
//            accountMainEntity.setUpdateTime(new Date());
//            int num = accountMainMapper.updateAccountMain(accountMainEntity);
//            if (num != 1) {
//                logger.error("#1[注册新账户]-[更新APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
//                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
//            }
//        }

        //建立账户详细信息
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(IDUtils.createID());
        accountEntity.setAppName(request.getAppName());
        accountEntity.setMobile(request.getMobile());
        accountEntity.setInviteCode(getUniqueInviteCode());
        accountEntity.setRegisterTime(new Date());
        accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
        accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
        accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
        accountEntity.setCreateTime(new Date());
        String channel = request.getChannel();
        if (StringUtil.isBlank(channel)) {
            channel = "web";
        }
        accountEntity.setChannel(channel);
        accountEntity.setCreateTime(new Date());
        int num = accountMapper.saveAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[注册新账户]-[插入APP账户详细数据失败]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 账户登录
     *
     * @param request
     * @return
     */
    @Override
    public AccountVO loginAccount(LoginAccountRequest request) {
//        //查询总账户是否存在
//        AccountMainEntity accountMainEntity = accountMainMapper.selectByMobile(request.getMobile());

//        //如果总账户不存在，则新建，若存在则更新数据
//        if (null == accountMainEntity) {
//            //插入新数据
//            accountMainEntity = new AccountMainEntity();
//            accountMainEntity.setAccountId(request.getAccountId());
//            accountMainEntity.setMobile(request.getMobile());
//            accountMainEntity.setCreateTime(new Date());
//            int num = accountMainMapper.saveAccountMain(accountMainEntity);
//            if (num != 1) {
//                logger.error("#1[注册新账户]-[插入APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
//                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
//            }
//        } else {
//            //更新总账户数据信息
//            accountMainEntity.setUpdateTime(new Date());
//            int num = accountMainMapper.updateAccountMain(accountMainEntity);
//            if (num != 1) {
//                logger.error("#1[注册新账户]-[更新APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
//                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
//            }
//        }


        AccountEntity accountEntity = accountMapper.selectByUuid(request.getUuid());
        if (StringUtil.equals(request.getAccountType(), AccountTypeEnum.VISITOR.getCode())) {
            //不存在游客账户，则新建一个游客账户
            if (null == accountEntity) {
                accountEntity = new AccountEntity();
                accountEntity.setAccountId(IDUtils.createID());
                accountEntity.setAppName(request.getAppName());
                accountEntity.setMobile("");
                accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
                accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
                accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
                accountEntity.setUuid(request.getUuid());
                accountEntity.setBrand(request.getBrand());
                accountEntity.setDeviceModel(request.getDeviceModel());
                accountEntity.setCreateTime(new Date());
                accountEntity.setCreateTime(new Date());
                //插入数据库表
                int saveNum = accountMapper.saveAccount(accountEntity);
                if (saveNum != 1) {
                    logger.error("#1[注册新账户]-[插入APP账户详细数据失败]-AccountEntity={}", accountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            }
        } else if (StringUtil.equals(request.getAccountType(), AccountTypeEnum.NORMAL.getCode())) {
            //查询账户是否存在,如果不存在则建立新账户
            AccountEntity newAccountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
            if (null == newAccountEntity) {
                //建立账户详细信息
                newAccountEntity = new AccountEntity();
                newAccountEntity.setAccountId(accountEntity.getAccountId());
                newAccountEntity.setAppName(accountEntity.getAppName());
                newAccountEntity.setMobile(request.getMobile());
                newAccountEntity.setInviteCode(getUniqueInviteCode());
                newAccountEntity.setRegisterTime(new Date());
                newAccountEntity.setNickName(accountEntity.getNickName());
                newAccountEntity.setType(accountEntity.getType());
                newAccountEntity.setStatus(accountEntity.getStatus());
                newAccountEntity.setUuid(accountEntity.getUuid());
                newAccountEntity.setBrand(request.getBrand());
                newAccountEntity.setDeviceModel(request.getDeviceModel());
                newAccountEntity.setUpdateTime(new Date());
                String channel = request.getChannel();
                if (StringUtil.isBlank(channel)) {
                    channel = "web";
                }
                newAccountEntity.setChannel(channel);
                //插入数据库表
                int updateNum = accountMapper.updateAccount(newAccountEntity);
                if (updateNum != 1) {
                    logger.error("#1[注册新账户]-[通过游客更新APP账户详细数据失败]-AccountEntity={}", newAccountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            }
        } else {
            logger.error("#1[账户登录]-[账户类型有误]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        //判断账户状态
        if (!(accountEntity.getStatus().equals(AccountStatusEnum.NORMAL.getCode()))) {
            logger.error("#1[账户登录]-[当前账户不可用]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        //用于APP两个月有效期控制
        String token = StringUtil.getRandomStringByLength(32);
        Date tokenExpireTime = DateUtil.getDiffDay(new Date(), 60);
        //更新用户的登录信息
        accountEntity.setToken(token);
        accountEntity.setTokenExpireTime(tokenExpireTime);
        accountEntity.setLastLoginTime(new Date());
        accountEntity.setLastLoginIp(request.getIp());
        accountEntity.setUpdateTime(new Date());
        int updateNum = accountMapper.updateAccount(accountEntity);
        if (updateNum != 1) {
            logger.error("#1[账户登录]-[登录失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode());
        }

        //返回登录结果
        AccountVO vo = new AccountVO();
        String encryptAccountId = accountEntity.getAccountId();
        vo.setAccountId(encryptAccountId);
        vo.setToken(token);
        return vo;
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @Override
    public AccountVO logoutAccount(BaseRequest request) {
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(request.getAccountId(), request.getAppName());

        if (null == accountEntity) {
            logger.error("#1[注销登录]-[账户信息未找到]-request={}", request);
            throw new BaseException(AccountErrorEnum.LOGOUT_ACCOUNT_FAILURE.getErrorCode());
        }
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountId(accountEntity.getAccountId());
        accountVO.setToken(accountEntity.getToken());
        return accountVO;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @Override
    public AccountDetailVO getAccountInfo(BaseRequest request) {
        //查询账户
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(request.getAccountId(), request.getAccountId());
        //验证token秘钥是否过期
        Date tokenExpireTime = accountEntity.getTokenExpireTime();
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[获取用户基本信息]-[TOKEN已过期]-request={}", request);
            throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
        }

        AccountDetailVO vo = new AccountDetailVO();
        vo.setAccountId(request.getAccountId());
        vo.setAppName(accountEntity.getAppName());
        vo.setMobile(accountEntity.getMobile());
        vo.setEmail(accountEntity.getEmail());
        vo.setNickname(accountEntity.getNickName());
        vo.setHeadImg(accountEntity.getHeadImg());
        vo.setSexual(accountEntity.getSexual());
        vo.setBirthday(DateUtil.getDateString(accountEntity.getBirthday(), DateUtil.DATE));
        vo.setAutograph(accountEntity.getAutograph());
        vo.setInviteCode(accountEntity.getInviteCode());
        vo.setInviteAccountId(accountEntity.getInviteAccountId());
        vo.setDeviceId(accountEntity.getUuid());
        vo.setRegisterTime(DateUtil.getDateString(accountEntity.getRegisterTime(), DateUtil.DATE_ANT_TIME_S));
        vo.setType(accountEntity.getType());
        vo.setStatus(accountEntity.getStatus());
        vo.setAutoPay(accountEntity.getAutoPay());
        vo.setVipExpireTime(accountEntity.getVipExpireTime());
        if (StringUtil.equals(accountEntity.getType(), AccountVipTypeEnum.VIP.getCode())) {
            //计算会员剩余天数
            Date vipExpireTime = accountEntity.getVipExpireTime();
            int days = 0;
            if (null != vipExpireTime) {

                double expriseDays = (double) (vipExpireTime.getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24);

                days = (int) Math.ceil(expriseDays);

            }
            vo.setVipExpireDays(days);
        }
        vo.setChannel(accountEntity.getChannel());
        vo.setLastLoginIp(accountEntity.getLastLoginIp());
        vo.setLastLoginTime(DateUtil.getDateString(accountEntity.getLastLoginTime(), DateUtil.DATE_ANT_TIME_S));
        vo.setContent(accountEntity.getContent());
        return vo;
    }

    /**
     * token获取新登录信息
     * @param request
     * @return
     */
    @Override
    public String getNewAccountId(TokenAccountRequest request) {
        AccountEntity accountEntity = accountMapper.selectByToken(request.getToken());
        String accountId = accountEntity.getAccountId();
        Date tokenExpireTime = accountEntity.getTokenExpireTime();
        //存在,且token没有过期,重新产生加密后的accountId
        if (null != accountEntity && tokenExpireTime.after(new Date())) {
            redisTemplate.delete(accountId);
            return accountId;
        }
        return null;
    }

    /**
     * 更新账户信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean updateAccountInfo(UpdateAccountRequest request) {
        String accountId = String.valueOf(redisTemplate.opsForValue().get(request.getAccountId()));
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(accountId);
        accountEntity.setAppName(request.getAppName());
        accountEntity.setNickName(request.getNickname());
        accountEntity.setHeadImg(request.getHeadImg());
        accountEntity.setSexual(request.getSexual());
        accountEntity.setBirthday(request.getBirthday());
        accountEntity.setAutograph(request.getAutograph());
        accountEntity.setType(request.getType());
        accountEntity.setVipExpireTime(request.getVipExpireTime());
        accountEntity.setUpdateTime(new Date());
        int num = accountMapper.updateAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[更新账户信息]-[更新失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 生成全平台唯一的邀请码
     *
     * @return
     */
    private String getUniqueInviteCode() {
        String inviteCode;

        //判断邀请码是否重复，重复则重新生成
        while (true) {
            inviteCode = StringUtil.getRandomCode(8);
            int count = accountMapper.countByInviteCode(inviteCode);
            if (count == 0) {
                break;
            }
        }
        return inviteCode;
    }
}
