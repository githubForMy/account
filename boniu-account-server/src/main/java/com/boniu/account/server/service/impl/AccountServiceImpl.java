package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountStatusEnum;
import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.enums.AccountVipInfoTypeEnum;
import com.boniu.account.api.enums.AccountVipTypeEnum;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountCancelVO;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountPushInfoVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.repository.api.AccountMainMapper;
import com.boniu.account.repository.api.AccountMapper;
import com.boniu.account.repository.api.AccountUuidMapper;
import com.boniu.account.repository.api.AccountVipInfoMapper;
import com.boniu.account.repository.entity.AccountEntity;
import com.boniu.account.repository.entity.AccountMainEntity;
import com.boniu.account.repository.entity.AccountUuidEntity;
import com.boniu.account.repository.entity.AccountVipInfoEntity;
import com.boniu.account.server.client.MarketingClient;
import com.boniu.account.server.client.PayClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.hepler.AccountVipHelper;
import com.boniu.account.server.pojo.AccountVipInfoPoJo;
import com.boniu.account.server.service.AccountService;
import com.boniu.base.utile.enums.BooleanEnum;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.exception.ErrorEnum;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.*;
import com.boniu.marketing.api.enums.ActionEnum;
import com.boniu.marketing.api.request.SubmitPushTaskByRTRequest;
import com.boniu.pay.api.request.UpdateAccountIdByUuidRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private AccountMapper accountMapper;

    @Resource
    private PayClient payClient;

    @Resource
    private MarketingClient marketingClient;

    @Resource
    private AccountUuidMapper accountUuidMapper;

    @Resource
    private AccountMainMapper accountMainMapper;

    @Value("${personal.head.image}")
    private String defaultHeadImg;

    @Resource
    private AccountVipHelper accountVipHelper;
    @Resource
    private AccountVipInfoMapper accountVipInfoMapper;


    /**
     * 账户登录
     *
     * @param request
     * @return
     */
    @Override
    public AccountVO loginAccount(LoginAccountRequest request) {

        AccountEntity accountEntity = new AccountEntity();
        //如果是游客状态
        if(request.getAccountType().equals(AccountTypeEnum.VISITOR.getCode())){
            accountEntity = accountMapper.selectByUuid(request.getUuid(), request.getAppName(), null);
            //不存在游客账户，则新建一个游客账户
            if (null == accountEntity) {
                accountEntity = new AccountEntity();
                String accountId = IDUtils.createID();
                accountEntity.setAccountId(accountId);
                accountEntity.setAppName(request.getAppName());
                accountEntity.setMobile(null);
                accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
                accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
                accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
                String visitorChannel = request.getVisitorChannel();
                if (StringUtil.isBlank(visitorChannel)) {
                    visitorChannel = "visitor_web";
                }
                accountEntity.setChannel(request.getChannel());
                accountEntity.setHeadImg(request.getHeadImg());
                accountEntity.setVisitorChannel(visitorChannel);
                accountEntity.setUuid(request.getUuid());
                accountEntity.setBrand(request.getBrand());
                accountEntity.setDeviceModel(request.getDeviceModel());
                accountEntity.setCreateTime(new Date());
                accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                accountEntity.setDataId(IDUtils.createID());
                accountEntity.setPlatform(request.getDeviceType());
                accountEntity.setTotalScore(0);
                accountEntity.setRemainScore(0);
                //插入数据库表
                int count = accountMapper.saveAccount(accountEntity);
                if (count == 0) {
                    logger.error("#1[注册新账户]-[游客账户创建数据库操作失败]-AccountEntity={}", accountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            }

        //如果是正常用户，则需要考虑手机号码是否绑定过
        }else if(request.getAccountType().equals(AccountTypeEnum.NORMAL.getCode())){
            //先通过手机号码查询账户
            accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
            if(null==accountEntity){
                //这个查询不会查询出不带有手机号码的数据
                accountEntity = accountMapper.selectByUuid(request.getUuid(), request.getAppName(), null);
                if (null == accountEntity) {
                    logger.error("#1[账户登录]-[当前uuid不存在游客账户]");
                    throw new BaseException(AccountErrorEnum.VISITOR_ACCOUNT_NOT_EXIST.getErrorCode());
                }
                //绑定uuid和mobile
                AccountEntity accountUpdate = new AccountEntity();
                accountUpdate.setAccountId(accountEntity.getAccountId());
                accountUpdate.setMobile(request.getMobile());
                accountUpdate.setInviteCode(getUniqueInviteCode());
                accountUpdate.setBrand(request.getBrand());
                accountUpdate.setDeviceModel(request.getDeviceModel());
                accountUpdate.setUpdateTime(new Date());
                accountUpdate.setRegisterTime(new Date());
                String channel = request.getChannel();
                if (StringUtil.isBlank(channel)) {
                    channel = "web";
                }
                accountUpdate.setChannel(channel);
                accountUpdate.setPlatform(request.getDeviceType());
                int count = accountMapper.updateAccount(accountUpdate);
                if(count==0){
                    logger.error("#1[注册新账户]-[uuid绑定mobile数据库操作失败]-request={}", request);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
//                accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
            }
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
        accountEntity.setPlatform(request.getDeviceType());
        accountEntity.setUpdateTime(new Date());
        int updateNum = accountMapper.updateAccount(accountEntity);
        if (updateNum != 1) {
            logger.error("#1[账户登录]-[登录失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode());
        }

        //返回登录结果
        AccountVO vo = new AccountVO();
        vo.setAccountId(accountEntity.getAccountId());
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
        AccountEntity accountEntity = new AccountEntity();
        //无accountId情况
        if (StringUtil.isBlank(request.getAccountId())) {
            //查询uuid与appName是否存在用户数据。若不存在则创建，存在则返回
            accountEntity = accountMapper.selectByUuid(request.getUuid(), request.getAppName(), BooleanEnum.YES.getCode());
            if (null == accountEntity) {
                accountEntity = new AccountEntity();
                accountEntity.setAppName(request.getAppName());
                accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
                accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
                accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
                accountEntity.setHeadImg(defaultHeadImg);
                accountEntity.setUuid(request.getUuid());
                accountEntity.setDeviceModel(request.getDeviceModel());
                accountEntity.setCreateTime(new Date());
                accountEntity.setDataId(IDUtils.createID());
                accountEntity.setPlatform(request.getBrand());
                accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                accountEntity.setTotalScore(0);
                accountEntity.setRemainScore(0);
                //插入数据库表
                int count = accountMapper.saveAccount(accountEntity);
                if (count == 0) {
                    logger.error("#1[关联新的游客账户]-[游客账户创建数据库操作失败]-AccountEntity={}", accountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            }
        } else {
            //查询账户
            accountEntity = accountMapper.selectByAccountIdAndAppName(request.getAccountId(), request.getAppName());
            if (null == accountEntity) {
                logger.error("#1[获取用户基本信息]-[未找到用户信息]-request={}", request);
                throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
            }

            //如果账户信息不存在数据统计编号，则更新用户的数据统计编号
            if (StringUtil.isBlank(accountEntity.getDataId())) {
                accountEntity.setDataId(IDUtils.createID());
                accountEntity.setUpdateTime(new Date());
                accountMapper.updateAccount(accountEntity);
            }
        }

        AccountDetailVO vo = new AccountDetailVO();
        vo.setAccountId(request.getAccountId());
        vo.setAppName(accountEntity.getAppName());
        vo.setMobile(accountEntity.getMobile());
        vo.setEmail(accountEntity.getEmail());
        vo.setNickname(accountEntity.getNickName());
        vo.setHeadImg(accountEntity.getHeadImg());
        vo.setSexual(accountEntity.getSexual());
        vo.setBirthday(accountEntity.getBirthday());
        vo.setAutograph(accountEntity.getAutograph());
        vo.setInviteCode(accountEntity.getInviteCode());
        vo.setInviteAccountId(accountEntity.getInviteAccountId());
        vo.setUuid(accountEntity.getUuid());
        vo.setRegisterTime(accountEntity.getRegisterTime());
        vo.setPlatform(accountEntity.getPlatform());
        vo.setStatus(accountEntity.getStatus());
        vo.setAutoPay(accountEntity.getAutoPay());
        vo.setApplyCancelTime(accountEntity.getApplyCancelTime());
        vo.setBrand(accountEntity.getBrand());
        vo.setTokenExpireTime(accountEntity.getTokenExpireTime());
        vo.setType(AccountVipInfoTypeEnum.NORMAL.getCode());
        AccountVipInfoPoJo accountVipInfoPoJo = accountVipHelper.getNowVipInfo(request.getAccountId(), request.getUuid(), request.getAppName());
        if (null != accountVipInfoPoJo) {
            vo.setVipExpireTime(accountVipInfoPoJo.getVipExpireTime());
            vo.setVipExpireDays(accountVipInfoPoJo.getVipExpireDays());
            vo.setType(accountVipInfoPoJo.getVipType());
            vo.setVipGroupInfos(accountVipInfoPoJo.getVipGroupInfos());
        }
        vo.setRemainScore(accountEntity.getRemainScore());
        vo.setTotalScore(accountEntity.getTotalScore());
        vo.setDataId(accountEntity.getDataId());
        vo.setChannel(accountEntity.getChannel());
        vo.setLastLoginIp(accountEntity.getLastLoginIp());
        vo.setLastLoginTime(accountEntity.getLastLoginTime());
        vo.setContent(accountEntity.getContent());
        vo.setTimes(accountVipInfoPoJo.getVipLimitTimes());
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

        if (null == accountEntity) {
            logger.error("#1[获取用户基本信息]-[未找到用户信息]-request={}", request);
            throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
        }

        Date tokenExpireTime = accountEntity.getTokenExpireTime();
        //存在,且token没有过期,重新产生加密后的accountId
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[获取用户基本信息]-[TOKEN已过期]-request={}", request);
            throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
        }

        String accountId = accountEntity.getAccountId();
        return accountId;
    }

    /**
     * 更新账户信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean updateAccountInfo(UpdateAccountRequest request) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(request.getAccountId());
        accountEntity.setAppName(request.getAppName());
        accountEntity.setNickName(request.getNickname());
        accountEntity.setHeadImg(request.getHeadImg());
        accountEntity.setSexual(request.getSexual());
        accountEntity.setBirthday(StringUtil.isBlank(request.getBirthday()) ? null : DateUtil.strToDate(request.getBirthday(), DateUtil.DATE));
        accountEntity.setAutograph(request.getAutograph());
        accountEntity.setType(request.getType());
        accountEntity.setVipExpireTime(request.getVipExpireTime());
        accountEntity.setStatus(request.getStatus());
        accountEntity.setUpdateTime(new Date());
        accountEntity.setTimes(request.getTimes());
        accountEntity.setAutoPay(request.getAutoPay());

        if(StringUtil.isBlank(request.getAccountId())){
            AccountEntity accountResult = accountMapper.selectByUuid(request.getUuid(), request.getAppName(), null);
            accountEntity.setId(accountResult.getId());

            int numById = accountMapper.updateAccountById(accountEntity);
            if (numById != 1) {
                logger.error("#1[更新账户信息]-[更新失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
            return true;
        }

        int num = accountMapper.updateAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[更新账户信息]-[更新失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 验证用户名是否已注册
     *
     * @param request
     * @return
     */
    @Override
    public Boolean checkUserName(CheckUserNameRequest request) {
        //判断用户名是否存在
        AccountEntity accountEntity = accountMapper.selectByUserName(request.getUserName());
        if (null != accountEntity) {
            logger.error("#1[注册账户]-[用户名不存在]-request={}", request);
            throw new BaseException(AccountErrorEnum.USERNAME_IS_NOT_EXIST.getErrorCode());
        }

        return true;
    }

    /**
     * 注册新账户(海外版本)
     *
     * @param request
     * @return
     */
    @Override
    public Boolean registerAccount(RegisterAccountRequest request) {
        //判断用户名是否存在
        AccountEntity accountEntity = accountMapper.selectByUserName(request.getUserName());
        if (null != accountEntity) {
            logger.error("#1[注册账户]-[用户名已存在]-request={}", request);
            throw new BaseException(AccountErrorEnum.USERNAME_IS_EXIST.getErrorCode());
        }

        AccountEntity newAccountEntity = new AccountEntity();
        newAccountEntity.setAccountId(request.getAccountId());
        newAccountEntity.setAppName(request.getAppName());
        newAccountEntity.setUserName(request.getUserName());
        newAccountEntity.setPassword(MD5Util.encrypt(request.getFirstPassword()));
        newAccountEntity.setInviteCode(getUniqueInviteCode());
        newAccountEntity.setUuid(request.getUuid());
        newAccountEntity.setRegisterTime(new Date());
        newAccountEntity.setType(AccountTypeEnum.NORMAL.getCode());
        newAccountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
        newAccountEntity.setBrand(request.getBrand());
        newAccountEntity.setDeviceModel(request.getDeviceModel());
        newAccountEntity.setUpdateTime(new Date());
        int num = accountMapper.updateAccount(newAccountEntity);
        if (num != 1) {
            logger.error("#1[注册账户]-[注册失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 登录新账户(海外版本)
     *
     * @param request
     * @return
     */
    @Override
    public AccountVO loginOverseasAccount(LoginOverseasAccountRequest request) {
        //用户名密码查找用户
        AccountEntity accountEntity = accountMapper.selectByUserNameAndPassword(request.getUserName(), request.getPassword());
        if (null == accountEntity) {
            logger.error("#1[登录账户]-[用户名或密码错误]-request={}", request);
            throw new BaseException(AccountErrorEnum.USERNAME_PWD_ERROR.getErrorCode());
        }

        //判断账户状态
        if (!(accountEntity.getStatus().equals(AccountStatusEnum.NORMAL.getCode()))) {
            logger.error("#1[登录账户]-[当前账户不可用]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        //用于APP两个月有效期控制
        String token = StringUtil.getRandomStringByLength(32);
        Date tokenExpireTime = DateUtil.getDiffDay(new Date(), 60);
        //更新用户的登录信息
        AccountEntity updateAccountEntity = new AccountEntity();
        updateAccountEntity.setAccountId(accountEntity.getAccountId());
        updateAccountEntity.setAppName(accountEntity.getAppName());
        updateAccountEntity.setToken(token);
        updateAccountEntity.setTokenExpireTime(tokenExpireTime);
        updateAccountEntity.setLastLoginTime(new Date());
        updateAccountEntity.setLastLoginIp(request.getIp());
        updateAccountEntity.setUpdateTime(new Date());
        int updateNum = accountMapper.updateAccount(updateAccountEntity);
        if (updateNum != 1) {
            logger.error("#1[登录账户]-[登录失败]-request={}", request);
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
     * 修改账户登录密码
     *
     * @param request
     * @return
     */
    @Override
    public Boolean modifyLoginPassword(UpdateLoginPasswordRequest request) {
        AccountEntity accountEntity = accountMapper.selectByUserNameAndPassword(request.getUsername(), request.getOldPassword());
        if (null == accountEntity) {
            logger.error("#1[修改账户登录密码]-[验证旧密码失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.OLD_PASSWORD_IS_WRONG.getErrorCode());
        }

        AccountEntity accountEntityUpdate = new AccountEntity();
        accountEntityUpdate.setAccountId(accountEntity.getAccountId());
        accountEntityUpdate.setAppName(accountEntity.getAppName());
        accountEntityUpdate.setPassword(MD5Util.encrypt(request.getFirstNewPassword()));
        accountEntityUpdate.setUpdateTime(new Date());
        int num = accountMapper.updateAccount(accountEntityUpdate);
        if (num != 1) {
            logger.error("#1[修改账户登录密码]-[失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 绑定邮箱地址到账户
     *
     * @param request
     * @return
     */
    @Override
    public Boolean bindEmail(BindEmailRequest request) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAppName(request.getAppName());
        accountEntity.setAccountId(request.getAccountId());
        accountEntity.setEmail(request.getEmail());
        accountEntity.setUpdateTime(new Date());
        int num = accountMapper.updateAccount(accountEntity);
        if (num != 1) {
            logger.error("#1[绑定邮箱地址到账户]-[失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 忘记密码找回-重设密码
     *
     * @param request
     * @return
     */
    @Override
    public Boolean resetPassword(ResetPasswordRequest request) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserName(request.getUserName());
        accountEntity.setPassword(MD5Util.encrypt(request.getFirstPassword()));
        accountEntity.setUpdateTime(new Date());
        int updateNum = accountMapper.resetPassword(accountEntity);
        if (updateNum != 1) {
            logger.error("#1[重设密码]-[失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.RESET_PASSWORD_FAIL.getErrorCode());
        }
        return true;
    }

    /**
     * 账户注销
     *
     * @param request
     * @return
     */
    @Override
    public AccountCancelVO cancelAccount(BaseRequest request) {
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(request.getAccountId(), request.getAppName());
        if (null == accountEntity) {
            logger.error("#1[账户注销]-[账户不存在]-request={}", request);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        //TODO:账户注销逻辑
        AccountEntity updateAccountEntity = new AccountEntity();
        updateAccountEntity.setAppName(request.getAppName());
        updateAccountEntity.setAccountId(request.getAccountId());
        updateAccountEntity.setApplyCancelTime(new Date());
        updateAccountEntity.setUpdateTime(new Date());
        accountMapper.updateAccount(updateAccountEntity);

        AccountCancelVO vo = new AccountCancelVO();
        vo.setMobile(accountEntity.getMobile());
        vo.setApplyTime(updateAccountEntity.getApplyCancelTime());
        return vo;
    }

    /**
     * 手机号码查询用户信息
     *
     * @param request
     * @return
     */
    @Override
    public AccountVO queryByMobile(QueryAccountByMobileRequest request) {
        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
        if (null == accountEntity) {
            return null;
        }
        AccountVO vo = new AccountVO();
        vo.setAccountId(accountEntity.getAccountId());
        vo.setToken(accountEntity.getToken());
        return vo;
    }

    /**
     * 保存账户
     *
     * @param request
     * @return
     */
    @Override
    public Boolean saveAccount(SaveAccountRequest request) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(request.getAccountId());
        accountEntity.setAppName(request.getAppName());
        accountEntity.setMobile(request.getMobile());
        accountEntity.setUuid(request.getUuid());
        accountEntity.setNickName(request.getNickName());
        accountEntity.setHeadImg(request.getHeadImg());
        accountEntity.setSexual(request.getBabySex());
        accountEntity.setBirthday(DateUtil.strToDate(request.getBabyBirthday(), DateUtil.DATE));
        accountEntity.setAutograph(request.getAutograph());
        accountEntity.setInviteCode(request.getInviteCode());
        accountEntity.setInviteAccountId(request.getInviteAccountId());
        accountEntity.setChannel(request.getChannel());
        accountEntity.setRegisterTime(request.getRegisterTime() == null ? new Date() : new Date(request.getRegisterTime()));
        accountEntity.setType(request.getType());
        accountEntity.setStatus(request.getStatus());
        accountEntity.setVipExpireTime(request.getVipExpireTime() == null ? null : new Date(request.getVipExpireTime()));
        accountEntity.setToken(request.getToken());
        accountEntity.setTokenExpireTime(request.getTokenExpireTime() == null ? null : new Date(request.getTokenExpireTime()));
        accountEntity.setLastLoginTime(request.getLastLoginTime() == null ? null : new Date(request.getLastLoginTime()));
        accountEntity.setLastLoginIp(request.getLastLoginIp());
        accountEntity.setBrand(request.getBrand());
        accountEntity.setDeviceModel(request.getDeviceModel());
        accountEntity.setCreateTime(request.getCreateTime() == null ? new Date() : new Date(request.getCreateTime()));
        accountEntity.setUpdateTime(request.getUpdateTime() == null ? null : new Date(request.getUpdateTime()));
        accountEntity.setDataId(IDUtils.createID());
        accountEntity.setAutoPay(BooleanEnum.NO.getCode());
        accountEntity.setTotalScore(0);
        accountEntity.setRemainScore(0);
        int num = accountMapper.saveAccount(accountEntity);
        if (num == 0) {
            logger.error("#1[保存账户]-[数据库操作失败]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
        return true;
    }

    /**
     * 查询注册邀请码是否存在
     *
     * @param request
     * @return
     */
    @Override
    public AccountDetailVO queryAccountByInviteCode(QueryAccountByInviteCodeRequest request) {
        AccountEntity accountEntity = accountMapper.selecyByInviteCode(request.getInviteCode());
        if (null == accountEntity) {
            return null;
        }

        //验证token秘钥是否过期
        Date tokenExpireTime = accountEntity.getTokenExpireTime();
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[获取用户基本信息]-[TOKEN已过期]-request={}", request);
            throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
        }

        AccountDetailVO vo = new AccountDetailVO();
        vo.setAccountId(accountEntity.getAccountId());
        vo.setAppName(accountEntity.getAppName());
        vo.setMobile(accountEntity.getMobile());
        vo.setEmail(accountEntity.getEmail());
        vo.setNickname(accountEntity.getNickName());
        vo.setHeadImg(accountEntity.getHeadImg());
        vo.setSexual(accountEntity.getSexual());
        vo.setBirthday(accountEntity.getBirthday());
        vo.setAutograph(accountEntity.getAutograph());
        vo.setInviteCode(accountEntity.getInviteCode());
        vo.setInviteAccountId(accountEntity.getInviteAccountId());
        vo.setUuid(accountEntity.getUuid());
        vo.setRegisterTime(accountEntity.getRegisterTime());
        vo.setType(accountEntity.getType());
        vo.setStatus(accountEntity.getStatus());
        vo.setAutoPay(accountEntity.getAutoPay());
        vo.setVipExpireTime(accountEntity.getVipExpireTime());
        vo.setApplyCancelTime(accountEntity.getApplyCancelTime());

        if (StringUtil.equals(accountEntity.getType(), AccountVipTypeEnum.VIP.getCode())) {
            //计算会员剩余天数
            Date vipExpireTime = accountEntity.getVipExpireTime();
            int days = 0;
            if (null != vipExpireTime) {

                double expriseDays = (double) (vipExpireTime.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);

                days = (int) Math.ceil(expriseDays);

            }
            vo.setVipExpireDays(days);
        }
        vo.setChannel(accountEntity.getChannel());
        vo.setLastLoginIp(accountEntity.getLastLoginIp());
        vo.setLastLoginTime(accountEntity.getLastLoginTime());
        vo.setContent(accountEntity.getContent());
        return vo;
    }

    @Override
    public Pagination<List<AccountDetailVO>> queryAccountList(QueryAccountListRequest request) {
        AccountEntity queryAccountEntity = new AccountEntity();
        queryAccountEntity.setAppName(request.getAppName());
        queryAccountEntity.setAppTitle(request.getAppTitle());
        queryAccountEntity.setAccountId(request.getAccountId());
        queryAccountEntity.setMobile(request.getMobile());
        queryAccountEntity.setType(request.getType());
        queryAccountEntity.setChannel(request.getChannel());
        queryAccountEntity.setStatus(request.getStatus());
        queryAccountEntity.setRegisterStartTime(request.getRegisterStartTime());
        queryAccountEntity.setRegisterEndTime(request.getRegisterEndTime());
        queryAccountEntity.setLastLoginStartTime(request.getLastLoginStartTime());
        queryAccountEntity.setLastLoginEndTime(request.getLastLoginEndTime());
        queryAccountEntity.setInviteAccountId(request.getInviteAccountId());

        List<AccountDetailVO> accountDetailVOS = new ArrayList<>();
        int totalCount = accountMapper.selectListCountBy(queryAccountEntity);

        Pagination pagination = new Pagination();
        Integer size = request.getPageSize();
        Integer page = request.getRequestPage();
        Integer pages = (page - 1) * size;
        pagination.doPage(page, totalCount, size);

        queryAccountEntity.setPage(pages);
        queryAccountEntity.setSize(size);
        List<AccountEntity> accountEntities = accountMapper.selectListBy(queryAccountEntity);
        if (CollectionUtils.isNotEmpty(accountEntities)) {
            for (AccountEntity accountEntity : accountEntities) {
                AccountDetailVO vo = new AccountDetailVO();
                vo.setAccountId(accountEntity.getAccountId());
                vo.setAppName(accountEntity.getAppName());
                vo.setMobile(accountEntity.getMobile());
                vo.setEmail(accountEntity.getEmail());
                vo.setNickname(accountEntity.getNickName());
                vo.setHeadImg(accountEntity.getHeadImg());
                vo.setSexual(accountEntity.getSexual());
                vo.setBirthday(accountEntity.getBirthday());
                vo.setAutograph(accountEntity.getAutograph());
                vo.setInviteCode(accountEntity.getInviteCode());
                vo.setInviteAccountId(accountEntity.getInviteAccountId());
                vo.setUuid(accountEntity.getUuid());
                vo.setRegisterTime(accountEntity.getRegisterTime());
                vo.setType(accountEntity.getType());
                vo.setStatus(accountEntity.getStatus());
                vo.setAutoPay(accountEntity.getAutoPay());
                vo.setVipExpireTime(accountEntity.getVipExpireTime());
                vo.setApplyCancelTime(accountEntity.getApplyCancelTime());

                if (StringUtil.equals(accountEntity.getType(), AccountVipTypeEnum.VIP.getCode())) {
                    //计算会员剩余天数
                    Date vipExpireTime = accountEntity.getVipExpireTime();
                    int days = 0;
                    if (null != vipExpireTime) {

                        double expriseDays = (double) (vipExpireTime.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);

                        days = (int) Math.ceil(expriseDays);

                    }
                    vo.setVipExpireDays(days);
                }
                vo.setChannel(accountEntity.getChannel());
                vo.setLastLoginIp(accountEntity.getLastLoginIp());
                vo.setLastLoginTime(accountEntity.getLastLoginTime());
                vo.setContent(accountEntity.getContent());
                accountDetailVOS.add(vo);
            }
        }
        pagination.setObject(accountDetailVOS);
        return pagination;
    }

    /**
     * 注册并登录账户
     * @param request
     * @return
     */
    @Override
    public AccountVO registerLoginAccount(RegisterLoginAccountRequest request) {
        //查询是否为已注册用户
        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
        AccountVO vo = new AccountVO();
        vo.setIsNew(BooleanEnum.NO.getCode());
        vo.setSyncStatus(BooleanEnum.NO.getCode());
        if (null == accountEntity) {
            vo.setIsNew(BooleanEnum.YES.getCode());
            accountEntity = accountMapper.selectByUuid(request.getUuid(), request.getAppName(), BooleanEnum.YES.getCode());
            String accountId = IDUtils.createID();
            if (null == accountEntity) {
                accountEntity = new AccountEntity();
                accountEntity.setAccountId(accountId);
                accountEntity.setAppName(request.getAppName());
                accountEntity.setMobile(request.getMobile());
                accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
                accountEntity.setInviteCode(getUniqueInviteCode());
                accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
                accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
                accountEntity.setInviteAccountId(request.getInviteAccountId());
                accountEntity.setHeadImg(request.getHeadImg());
                String channel = request.getChannel();
                if (StringUtil.isBlank(channel)) {
                    channel = "web";
                }
                accountEntity.setChannel(channel);
                accountEntity.setVisitorChannel(channel);
                accountEntity.setRegisterTime(new Date());
                accountEntity.setUuid(request.getUuid());
                accountEntity.setBrand(request.getBrand());
                accountEntity.setDeviceModel(request.getDeviceModel());
                accountEntity.setPlatform(request.getPlatform().toUpperCase());
                accountEntity.setCreateTime(new Date());
                accountEntity.setDataId(IDUtils.createID());
                accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                accountEntity.setTotalScore(0);
                accountEntity.setRemainScore(0);
                //插入数据库表
                int count = accountMapper.saveAccount(accountEntity);
                if (count == 0) {
                    logger.error("#1[注册新账户]-[数据库插入操作失败]-AccountEntity={}", accountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            } else {
                accountEntity.setAccountId(accountId);
                accountEntity.setMobile(request.getMobile());
                accountEntity.setInviteCode(getUniqueInviteCode());
                accountEntity.setInviteAccountId(request.getInviteAccountId());
                accountEntity.setChannel(request.getChannel());
                accountEntity.setVisitorChannel(request.getChannel());
                accountEntity.setRegisterTime(new Date());
                accountEntity.setPlatform(request.getPlatform().toUpperCase());
                accountEntity.setUpdateTime(new Date());
                accountEntity.setDataId(IDUtils.createID());
                //更新数据库表
                int count = accountMapper.updateAccountById(accountEntity);
                if (count == 0) {
                    logger.error("#1[注册新账户]-[数据库更新操作失败]-AccountEntity={}", accountEntity);
                    throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                }
            }
            SubmitPushTaskByRTRequest submitPushTaskByRTRequest = new SubmitPushTaskByRTRequest();
            submitPushTaskByRTRequest.setAppName(request.getAppName());
            submitPushTaskByRTRequest.setUuid(request.getUuid());
            submitPushTaskByRTRequest.setAccountId(accountId);
            submitPushTaskByRTRequest.setDeviceType(request.getPlatform().toUpperCase());
            submitPushTaskByRTRequest.setAction(ActionEnum.REGISTRY.getCode());

            BaseResponse<Boolean> response = marketingClient.pushNow(submitPushTaskByRTRequest);
            if (null == response || !response.isSuccess() || null == response.getResult()) {
                logger.error("#1[账户登录]-[发送注册成功通知失败]-request={}", submitPushTaskByRTRequest);
                throw new BaseException(response);
            }

        }

        //判断账户状态
        if (!(accountEntity.getStatus().equals(AccountStatusEnum.NORMAL.getCode()))) {
            logger.error("#1[账户登录]-[当前账户不可用]-AccountEntity={}", accountEntity);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        //首次登陆匹配游客账号是否购买会员
        if (StringUtil.isBlank(accountEntity.getLastLoginIp()) || null == accountEntity.getLastLoginTime()) {
            vo.setIsNew(BooleanEnum.YES.getCode());
            //查询注册手机号对应的uuid是否存在会员信息
            AccountVipInfoEntity entityQuery = new AccountVipInfoEntity();
            entityQuery.setAppName(request.getAppName());
            entityQuery.setIsUseing(BooleanEnum.YES.getCode());
            entityQuery.setAccountIdNull(BooleanEnum.YES.getCode());
            entityQuery.setUuid(request.getUuid());

            List<AccountVipInfoEntity> list = accountVipInfoMapper.getVipInfoBy(entityQuery);
            if (list.size() > 0) {
                AccountVipInfoEntity temp = list.get(0);
                AccountVipInfoEntity vipInfoUpdate = new AccountVipInfoEntity();
                vipInfoUpdate.setAccountId(accountEntity.getAccountId());
                vipInfoUpdate.setAccountVipId(temp.getAccountVipId());
                accountVipInfoMapper.updateVipInfo(vipInfoUpdate);

                //恢复订单表，苹果票据表，苹果购买记录表
                UpdateAccountIdByUuidRequest updateAccountIdByUuidRequest = new UpdateAccountIdByUuidRequest();
                updateAccountIdByUuidRequest.setAccountId(accountEntity.getAccountId());
                updateAccountIdByUuidRequest.setAppName(accountEntity.getAppName());
                updateAccountIdByUuidRequest.setUuid(accountEntity.getUuid());
                BaseResponse<Boolean> response = payClient.batchUpdateAccountIdByUuid(updateAccountIdByUuidRequest);
                if (null == response || !response.isSuccess() || null == response.getResult()) {
                    logger.error("#1[账户登录]-[恢复苹果购买记录表、订单表、票据表失败]-baseRequest={}", updateAccountIdByUuidRequest);
                    throw new BaseException(response);
                }
            }
        }

        //登录记录uuid
        AccountUuidEntity accountUuidEntity = new AccountUuidEntity();
        accountUuidEntity.setAppName(request.getAppName());
        accountUuidEntity.setAccountId(accountEntity.getAccountId());
        accountUuidEntity.setUuid(request.getUuid());
        accountUuidEntity.setBrand(request.getBrand());
        accountUuidEntity.setDeviceModel(request.getDeviceModel());
        accountUuidEntity.setPlatform(request.getPlatform().toUpperCase());
        accountUuidEntity.setIp(request.getIp());
        accountUuidEntity.setCreateTime(new Date());
        int num = accountUuidMapper.saveUuid(accountUuidEntity);
        if (num != 1) {
            logger.error("#1[新增设备信息]-[插入设备信息失败]-UuidEntity={}", accountUuidEntity);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        //用于APP两个月有效期控制
        String token = StringUtil.getRandomStringByLength(32);
        Date tokenExpireTime = DateUtil.getDiffDay(new Date(), 60);
        //更新用户的登录信息
        accountEntity.setUuid(request.getUuid());
        accountEntity.setPlatform(request.getPlatform());
        accountEntity.setToken(token);
        accountEntity.setTokenExpireTime(tokenExpireTime);
        accountEntity.setLastLoginTime(new Date());
        accountEntity.setLastLoginIp(request.getIp());
        accountEntity.setUpdateTime(new Date());
        if (StringUtil.isBlank(accountEntity.getDataId())) {
            accountEntity.setDataId(IDUtils.createID());
        }
        int updateNum = accountMapper.updateAccount(accountEntity);
        if (updateNum != 1) {
            logger.error("#1[账户登录]-[登录失败]-accountId={}", accountEntity.getAccountId());
            throw new BaseException(AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode());
        }

        //创建或更新主账户信息
        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setMobile(request.getMobile());
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        //若手机号不存在，则创建新主账号信息，否则更新
        if (null == accountMainEntity) {
            accountMainEntity = new AccountMainEntity();
            accountMainEntity.setAccountMainId(IDUtils.createID());
            accountMainEntity.setMobile(request.getMobile());
            accountMainEntity.setNickname("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(5, true, false));
            accountMainEntity.setHeadImg(defaultHeadImg);
            accountMainEntity.setTotalScore(0);
            accountMainEntity.setRemainScore(0);
            accountMainEntity.setCreateTime(new Date());
            int saveNum = accountMainMapper.save(accountMainEntity);
            if (saveNum != 1) {
                logger.error("#1[写入主账户数据]-[插入数据失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        } else {
            accountMainEntity.setAccountMainId(accountMainEntity.getAccountMainId());
            accountMainEntity.setUpdateTime(new Date());
            int updateCount = accountMainMapper.update(accountMainEntity);
            if (updateCount != 1) {
                logger.error("#1更新主账户数据]-[更新数据失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        }

        //返回登录结果
        vo.setAccountId(accountEntity.getAccountId());
        vo.setToken(token);
        return vo;
    }

    /**
     * 过期VIP账户为普通账户
     * @return
     */
    @Override
    public Boolean vipAccountExpire() {
        List<AccountEntity> accountEntities = accountMapper.selectExpireAccountList();
        List<String> expireAccountIds = new ArrayList<>();
        //获取已过期的账户id
        if (CollectionUtils.isNotEmpty(accountEntities)) {
            for (AccountEntity accountEntity : accountEntities) {
                String accountId = accountEntity.getAccountId();
                expireAccountIds.add(accountId);
            }
        }
        //通过accountIds更新会员过期用户的状态
        if (CollectionUtils.isNotEmpty(expireAccountIds)) {
            accountMapper.updateTypeByAccountIds(expireAccountIds, AccountTypeEnum.NORMAL.getCode());
        }
        return null;
    }

    /**
     * 清理注销申请时间为空
     */
    @Override
    public void clearCancelTime() {
        List<AccountEntity> accountEntities = accountMapper.selectCancelAccountList();
        List<String> cancelAccountIds = new ArrayList<>();
        //获取已注销申请的账户id
        if (CollectionUtils.isNotEmpty(accountEntities)) {
            for (AccountEntity accountEntity : accountEntities) {
                String accountId = accountEntity.getAccountId();
                cancelAccountIds.add(accountId);
            }
        }
        //通过accountIds更新用户注销申请时间为空
        if (CollectionUtils.isNotEmpty(cancelAccountIds)) {
            accountMapper.updateApplyCancelTimeByAccountIds(cancelAccountIds);
        }
    }
    /**
     * 根据参数查询用户信息
     * @param request
     * @return
     */
    @Override
    public List<AccountDetailVO> queryAccountListBy(QueryAccountListByRequest request) {
        AccountEntity queryAccountEntity = new AccountEntity();
        queryAccountEntity.setAppName(request.getAppName());
        queryAccountEntity.setAccountId(request.getAccountId());
        queryAccountEntity.setMobile(request.getMobile());
        queryAccountEntity.setType(request.getType());
        queryAccountEntity.setChannel(request.getChannel());
        queryAccountEntity.setStatus(request.getStatus());
        queryAccountEntity.setRegisterStartTime(request.getRegisterStartTime());
        queryAccountEntity.setRegisterEndTime(request.getRegisterEndTime());
        queryAccountEntity.setLastLoginStartTime(request.getLastLoginStartTime());
        queryAccountEntity.setLastLoginEndTime(request.getLastLoginEndTime());
        queryAccountEntity.setInviteAccountId(request.getInviteAccountId());

        List<AccountDetailVO> accountDetailVOS = new ArrayList<>();

        List<AccountEntity> accountEntities = accountMapper.selectListBy(queryAccountEntity);

        if (CollectionUtils.isNotEmpty(accountEntities)) {
            for (AccountEntity accountEntity : accountEntities) {
                AccountDetailVO vo = new AccountDetailVO();
                vo.setAccountId(accountEntity.getAccountId());
                vo.setAppName(accountEntity.getAppName());
                vo.setMobile(accountEntity.getMobile());
                vo.setEmail(accountEntity.getEmail());
                vo.setNickname(accountEntity.getNickName());
                vo.setHeadImg(accountEntity.getHeadImg());
                vo.setSexual(accountEntity.getSexual());
                vo.setBirthday(accountEntity.getBirthday());
                vo.setAutograph(accountEntity.getAutograph());
                vo.setInviteCode(accountEntity.getInviteCode());
                vo.setInviteAccountId(accountEntity.getInviteAccountId());
                vo.setUuid(accountEntity.getUuid());
                vo.setRegisterTime(accountEntity.getRegisterTime());
                vo.setType(accountEntity.getType());
                vo.setStatus(accountEntity.getStatus());
                vo.setAutoPay(accountEntity.getAutoPay());
                vo.setVipExpireTime(accountEntity.getVipExpireTime());
                vo.setApplyCancelTime(accountEntity.getApplyCancelTime());

                if (StringUtil.equals(accountEntity.getType(), AccountVipTypeEnum.VIP.getCode())) {
                    //计算会员剩余天数
                    Date vipExpireTime = accountEntity.getVipExpireTime();
                    int days = 0;
                    if (null != vipExpireTime) {

                        double expriseDays = (double) (vipExpireTime.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);

                        days = (int) Math.ceil(expriseDays);

                    }
                    vo.setVipExpireDays(days);
                }
                vo.setChannel(accountEntity.getChannel());
                vo.setLastLoginIp(accountEntity.getLastLoginIp());
                vo.setLastLoginTime(accountEntity.getLastLoginTime());
                vo.setContent(accountEntity.getContent());
                accountDetailVOS.add(vo);
            }
        }
        return accountDetailVOS;
    }

    /**
     * 根据参数分页查询账户信息列表(管理后台)
     *
     * @param request
     * @return
     */
    @Override
    public Pagination<List<AccountDetailVO>> queryAccountListForAdmin(QueryAccountListForAdminRequest request) {
        String appTitle="";

        if(CollectionUtils.isNotEmpty(request.getList())){
            List<QueryAccountByAppTitleRequest> list = request.getList();

            for (int i = 0; i <list.size() ; i++) {
                if(i==0){
                    appTitle = " AND ( app_name= '" + list.get(i).getAppName() + "' AND platform='" + list.get(i).getPlatform() + "' ";
                }else if(i==list.size()-1){
                    appTitle+=" OR app_name='"+list.get(i).getAppName()+"' AND platform='"+list.get(i).getPlatform()+"' )";
                }else {
                    appTitle+=" OR app_name='"+list.get(i).getAppName()+"' AND platform='"+list.get(i).getPlatform()+"' ";
                }

            }
            if(list.size()==1){
                appTitle+=" ) ";
            }

        }

        AccountEntity queryAccountEntity = new AccountEntity();
        queryAccountEntity.setAppTitle(appTitle);
        queryAccountEntity.setMobile(request.getMobile());

        List<AccountDetailVO> accountDetailVOS = new ArrayList<>();


        Pagination pagination = new Pagination();
        Integer size = request.getPageSize();
        Integer page = request.getRequestPage();
        Integer pages = (page - 1) * size;


        queryAccountEntity.setPage(pages);
        queryAccountEntity.setSize(size);
        List<AccountEntity> accountEntities = accountMapper.adminSelectListBy(queryAccountEntity);
        int totalCount = accountMapper.adminSelectListCountBy(queryAccountEntity);

        if (CollectionUtils.isNotEmpty(accountEntities)) {
            for (AccountEntity accountEntity : accountEntities) {
                AccountDetailVO vo = new AccountDetailVO();
                vo.setAccountId(accountEntity.getAccountId());
                vo.setAppName(accountEntity.getAppName());
                vo.setMobile(accountEntity.getMobile());
                vo.setEmail(accountEntity.getEmail());
                vo.setNickname(accountEntity.getNickName());
                vo.setHeadImg(accountEntity.getHeadImg());
                vo.setSexual(accountEntity.getSexual());
                vo.setBirthday(accountEntity.getBirthday());
                vo.setAutograph(accountEntity.getAutograph());
                vo.setInviteCode(accountEntity.getInviteCode());
                vo.setInviteAccountId(accountEntity.getInviteAccountId());
                vo.setUuid(accountEntity.getUuid());
                vo.setRegisterTime(accountEntity.getRegisterTime());
                vo.setType(AccountVipTypeEnum.NORMAL.getCode());
                AccountVipInfoPoJo accountVipInfoPoJo = accountVipHelper.getNowVipInfo(accountEntity.getAccountId(), accountEntity.getUuid(), accountEntity.getAppName());
                if (null != accountVipInfoPoJo) {
                    vo.setVipExpireTime(accountVipInfoPoJo.getVipExpireTime());
                    vo.setVipExpireDays(accountVipInfoPoJo.getVipExpireDays());
                    vo.setType(accountVipInfoPoJo.getVipType());
                }
                vo.setStatus(accountEntity.getStatus());
                vo.setAutoPay(accountEntity.getAutoPay());
                vo.setPlatform(accountEntity.getPlatform());
                vo.setApplyCancelTime(accountEntity.getApplyCancelTime());
                vo.setPlatform(accountEntity.getPlatform());
                vo.setChannel(accountEntity.getChannel());
                vo.setLastLoginIp(accountEntity.getLastLoginIp());
                vo.setLastLoginTime(accountEntity.getLastLoginTime());
                vo.setContent(accountEntity.getContent());
                accountDetailVOS.add(vo);
            }
        }

        pagination.doPage(page, totalCount, size);
        pagination.setObject(accountDetailVOS);
        return pagination;
    }

    /**
     * 获取推送目标
     *
     * @param dataIds
     * @return
     */
    @Override
    public List<AccountPushInfoVO> listPushInfo(List<String> dataIds) {

        List<AccountEntity> accountEntities = accountMapper.selectByDataIds(dataIds);

        List<AccountPushInfoVO> list = accountEntities.stream().map(e -> {
            AccountPushInfoVO vo = new AccountPushInfoVO();
            vo.setPlatform(e.getPlatform());
            vo.setUuid(e.getUuid());
            return vo;
        }).collect(Collectors.toList());

        return list;
    }

    /**
     * 取消用户会员信息
     *
     * @param request
     * @return
     */
    @Override
    public String cancelAccountVipInfo(CancelAccountVipInfoRequest request) {
        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());
        if (accountEntity == null) {
            return "未查询到会员信息";
        }

        accountVipHelper.cancelAccountVipInfo(accountEntity.getAccountId());

        return "取消成功";
    }

    /**
     * 清除用户信息
     *
     * @param request
     * @return
     */
    @Override
    public String deleteAccount(CancelAccountVipInfoRequest request) {


        AccountEntity accountEntity = accountMapper.selectByMobileAndAppName(request.getMobile(), request.getAppName());

        if (accountEntity == null) {
            return "未查询到用户信息";
        }

        AccountEntity update = new AccountEntity();
        update.setAccountId(accountEntity.getAccountId());

        long newMobile = new Date().getTime();

        update.setMobile(String.valueOf(newMobile).substring(0, 11));


        accountMapper.updateAccount(update);

        return "操作成功";
    }

    /**
     * 更新用户积分信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean updateAccountScore(UpdateAccountScoreRequest request) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(request.getAccountId());
        accountEntity.setTotalScore(request.getTotalScore());
        accountEntity.setRemainScore(request.getRemainScore());
        int num = accountMapper.updateScore(accountEntity);
        if (num != 1) {
            logger.error("#1[更新用户积分信息]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        return true;
    }

    /**
     * 批量获取账户详细信息
     *
     * @param request
     * @return
     */
    @Override
    public List<AccountDetailVO> listAccountInfo(List<BaseRequest> request) {
        List<AccountDetailVO> list = new ArrayList<>(request.size());

        for (BaseRequest baseRequest : request) {
            AccountEntity accountEntity = null;
            //无accountId情况
            if (StringUtil.isBlank(baseRequest.getAccountId())) {
                //查询uuid与appName是否存在用户数据。若不存在则创建，存在则返回
                accountEntity = accountMapper.selectByUuid(baseRequest.getUuid(), baseRequest.getAppName(), BooleanEnum.YES.getCode());
                if (null == accountEntity) {
                    accountEntity = new AccountEntity();
                    accountEntity.setAppName(baseRequest.getAppName());
                    accountEntity.setNickName("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(6, true, false));
                    accountEntity.setType(AccountVipTypeEnum.NORMAL.getCode());
                    accountEntity.setStatus(AccountStatusEnum.NORMAL.getCode());
                    accountEntity.setHeadImg(defaultHeadImg);
                    accountEntity.setUuid(baseRequest.getUuid());
                    accountEntity.setDeviceModel(baseRequest.getDeviceModel());
                    accountEntity.setCreateTime(new Date());
                    accountEntity.setDataId(IDUtils.createID());
                    accountEntity.setPlatform(baseRequest.getBrand());
                    accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                    accountEntity.setTotalScore(0);
                    accountEntity.setRemainScore(0);
                    //插入数据库表
                    int count = accountMapper.saveAccount(accountEntity);
                    if (count == 0) {
                        logger.error("#1[关联新的游客账户]-[游客账户创建数据库操作失败]-AccountEntity={}", accountEntity);
                        throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
                    }
                }
            } else {
                //查询账户
                accountEntity = accountMapper.selectByAccountIdAndAppName(baseRequest.getAccountId(), baseRequest.getAppName());
                if (null == accountEntity) {
                    logger.error("#1[获取用户基本信息]-[未找到用户信息]-request={}", request);
                    throw new BaseException(ErrorEnum.PLEASE_RELOGIN.getErrorCode());
                }

                //如果账户信息不存在数据统计编号，则更新用户的数据统计编号
                if (StringUtil.isBlank(accountEntity.getDataId())) {
                    accountEntity.setDataId(IDUtils.createID());
                    accountEntity.setUpdateTime(new Date());
                    accountMapper.updateAccount(accountEntity);
                }
            }

            AccountDetailVO vo = new AccountDetailVO();
            vo.setAccountId(baseRequest.getAccountId());
            vo.setAppName(accountEntity.getAppName());
            vo.setMobile(accountEntity.getMobile());
            vo.setEmail(accountEntity.getEmail());
            vo.setNickname(accountEntity.getNickName());
            vo.setHeadImg(accountEntity.getHeadImg());
            vo.setSexual(accountEntity.getSexual());
            vo.setBirthday(accountEntity.getBirthday());
            vo.setAutograph(accountEntity.getAutograph());
            vo.setInviteCode(accountEntity.getInviteCode());
            vo.setInviteAccountId(accountEntity.getInviteAccountId());
            vo.setUuid(accountEntity.getUuid());
            vo.setRegisterTime(accountEntity.getRegisterTime());
            vo.setPlatform(accountEntity.getPlatform());
            vo.setStatus(accountEntity.getStatus());
            vo.setAutoPay(accountEntity.getAutoPay());
            vo.setApplyCancelTime(accountEntity.getApplyCancelTime());
            vo.setBrand(accountEntity.getBrand());
            vo.setTokenExpireTime(accountEntity.getTokenExpireTime());
            vo.setType(AccountVipInfoTypeEnum.NORMAL.getCode());
            AccountVipInfoPoJo accountVipInfoPoJo = accountVipHelper.getNowVipInfo(baseRequest.getAccountId(), baseRequest.getUuid(), baseRequest.getAppName());
            if (null != accountVipInfoPoJo) {
                vo.setVipExpireTime(accountVipInfoPoJo.getVipExpireTime());
                vo.setVipExpireDays(accountVipInfoPoJo.getVipExpireDays());
                vo.setType(accountVipInfoPoJo.getVipType());
                vo.setVipGroupInfos(accountVipInfoPoJo.getVipGroupInfos());
            }
            vo.setRemainScore(accountEntity.getRemainScore());
            vo.setTotalScore(accountEntity.getTotalScore());
            vo.setDataId(accountEntity.getDataId());
            vo.setChannel(accountEntity.getChannel());
            vo.setLastLoginIp(accountEntity.getLastLoginIp());
            vo.setLastLoginTime(accountEntity.getLastLoginTime());
            vo.setContent(accountEntity.getContent());
            vo.setTimes(accountVipInfoPoJo.getVipLimitTimes());
            list.add(vo);
        }
        return list;
    }


    /**
     * 获取非会员用户信息
     *
     * @param request
     * @return
     */
    @Override
    public List<String> listNormalAccountInfo(BaseRequest request) {

        List<String> accountEntities = accountMapper.selectNormalAccount(request.getAppName(), request.getDeviceType());


        return accountEntities;
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
