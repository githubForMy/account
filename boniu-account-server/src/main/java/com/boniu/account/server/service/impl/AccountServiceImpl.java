package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountStatusEnum;
import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.repository.api.AccountMainMapper;
import com.boniu.account.repository.api.AccountMapper;
import com.boniu.account.repository.entity.AccountEntity;
import com.boniu.account.repository.entity.AccountMainEntity;
import com.boniu.account.server.client.MessageClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.exception.ErrorEnum;
import com.boniu.base.utile.helper.RedisHelper;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import com.boniu.message.api.enums.MessageVerifyTypeEnum;
import com.boniu.message.api.request.CheckVerifyCodeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName AccountServiceImpl
 * @Description 账户相关接口实现类
 * @Author HanXin
 * @Date 2019-07-02
 */

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountMainMapper accountMainMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private RedisHelper redisHelper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MessageClient messageClient;

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
        //验证验证码信息
        CheckVerifyCodeRequest checkVerifyCodeRequest = new CheckVerifyCodeRequest();
        checkVerifyCodeRequest.setMobile(request.getMobile());
        checkVerifyCodeRequest.setAppName(request.getAppName());
        checkVerifyCodeRequest.setVerifyCode(request.getVerifyCode());
        checkVerifyCodeRequest.setVerifyCodeType(MessageVerifyTypeEnum.LOGIN.getCode());
        messageClient.checkVerifyCode(checkVerifyCodeRequest);

        //查询总账户是否存在
        AccountMainEntity accountMainEntity = accountMainMapper.selectByMobile(request.getMobile());

        //如果总账户不存在，则新建，若存在则更新数据
        if (null == accountMainEntity) {
            //插入新数据
            accountMainEntity = new AccountMainEntity();
            accountMainEntity.setAccountId(IDUtils.createID());
            accountMainEntity.setMobile(request.getMobile());
            accountMainEntity.setCreateTime(new Date());
            int num = accountMainMapper.saveAccountMain(accountMainEntity);
            if (num != 1) {
                logger.error("#1[注册新账户]-[插入APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        } else {
            //更新总账户数据信息
            accountMainEntity.setUpdateTime(new Date());
            int num = accountMainMapper.updateAccountMain(accountMainEntity);
            if (num != 1) {
                logger.error("#1[注册新账户]-[更新APP总账户数据失败]-AccountMainEntity={}", accountMainEntity);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        }

        //建立账户详细信息
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(accountMainEntity.getAccountId());
        accountEntity.setAppName(request.getAppName());
        accountEntity.setMobile(request.getMobile());
        accountEntity.setInviteCode(getUniqueInviteCode());
        accountEntity.setRegisterTime(new Date());
        accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
        accountEntity.setType(AccountTypeEnum.NORMAL.getCode());
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
    public AccountVO loginAccount(LoginAccountRequest request, HttpServletRequest httpServletRequest) {
        //验证验证码信息
        CheckVerifyCodeRequest checkVerifyCodeRequest = new CheckVerifyCodeRequest();
        checkVerifyCodeRequest.setMobile(request.getMobile());
        checkVerifyCodeRequest.setAppName(request.getAppName());
        checkVerifyCodeRequest.setVerifyCode(request.getVerifyCode());
        checkVerifyCodeRequest.setVerifyCodeType(MessageVerifyTypeEnum.LOGIN.getCode());
        messageClient.checkVerifyCode(checkVerifyCodeRequest);

        //查询账户是否存在
        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
        if (null == accountEntity) {
            logger.error("#1[账户登录]-[账户不存在]");
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_NOT_EXIST.getErrorCode());
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
        accountEntity.setLastLoginIp(StringUtil.getIp(httpServletRequest));
        accountEntity.setUpdateTime(new Date());
        int num = accountMapper.updateAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[账户登录]-[登录失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.ACCOUNT_LOGIN_FAILURE.getErrorCode());
        }
        //返回登录结果
        AccountVO vo = new AccountVO();
        String encryptAccountId = redisHelper.encryptAccountId(accountEntity.getAccountId());
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
    public Boolean logoutAccount(BaseRequest request) {
        String accountId = String.valueOf(redisTemplate.opsForValue().get(request.getAccountId()));
        if (!StringUtil.isBlank(accountId)) {
            redisTemplate.delete(request.getAccountId());
        }
        return true;
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
        String accountId = redisHelper.decodeAccountId(request.getAccountId());
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(accountId, request.getAccountId());
        //验证token秘钥是否过期
        Date tokenExpireTime = accountEntity.getTokenExpireTime();
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[获取用户基本信息]-[TOKEN已过期]-request={}", request);
            throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
        }

        AccountDetailVO vo = new AccountDetailVO();
        vo.setAccountId(accountId);
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
        vo.setDeviceId(accountEntity.getDeviceId());
        vo.setRegisterTime(DateUtil.getDateString(accountEntity.getRegisterTime(), DateUtil.DATE_ANT_TIME_S));
        vo.setType(accountEntity.getType());
        vo.setStatus(accountEntity.getStatus());
        vo.setAutoPay(accountEntity.getAutoPay());
        if (StringUtil.equals(accountEntity.getType(), AccountTypeEnum.VIP.getCode())) {
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
     * 获取新的加密accountId
     *
     * @param request
     * @return
     */
    @Override
    public String getNewAccountId(TokenAccountRequest request) {
        AccountEntity accountEntity = accountMapper.selectByTokenAndMobile(request.getToken(), request.getMobile());

        //存在,且token没有过期,重新产生加密后的accountId
        if (null != accountEntity && accountEntity.getTokenExpireTime().after(new Date())) {
            redisTemplate.delete(accountEntity.getAccountId());
            String accountId = redisHelper.encryptAccountId(accountEntity.getAccountId());
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
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(accountId, request.getAppName());
        accountEntity.setNickName(request.getNickname());
        accountEntity.setHeadImg(request.getHeadImg());
        accountEntity.setSexual(request.getSexual());
        accountEntity.setBirthday(request.getBirthday());
        accountEntity.setAutograph(request.getAutograph());
        int num = accountMapper.updateAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[更新账户信息]-[更新失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 创建游客账户信息
     *
     * @return
     */
    @Override
    public String createVisitor(CreateVisitorAccountRequest request) {
        String deviceId = UUID.randomUUID().toString().replaceAll("-", "");

        //创建游客账户
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAppName(request.getAppName());
        accountEntity.setInviteCode(getUniqueInviteCode());
        accountEntity.setRegisterTime(new Date());
        accountEntity.setType(AccountTypeEnum.NORMAL.getCode());
        accountEntity.setStatus(AccountStatusEnum.VISITOR.getCode());
        accountEntity.setDeviceId(deviceId);
        accountEntity.setCreateTime(new Date());

        int num = accountMapper.saveAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[创建游客账户]-[插入APP账户详细数据失败]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        //返回游客账号
        return deviceId;
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
