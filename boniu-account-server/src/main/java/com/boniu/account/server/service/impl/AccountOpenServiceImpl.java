package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountOpenPartnerStateEnum;
import com.boniu.account.api.request.OpenAccountGetRequest;
import com.boniu.account.api.request.OpenAuthorizedLoginRequest;
import com.boniu.account.api.request.OpenGetAccessTokenRequest;
import com.boniu.account.api.request.OpenUpdateAccountRequest;
import com.boniu.account.api.vo.OpenAccessTokenVO;
import com.boniu.account.api.vo.OpenAccountVO;
import com.boniu.account.api.vo.OpenAuthorizedLoginVO;
import com.boniu.account.repository.api.AccountMainMapper;
import com.boniu.account.repository.api.AccountMainOpenMapper;
import com.boniu.account.repository.api.AccountOpenPartnerMapper;
import com.boniu.account.repository.entity.AccountMainEntity;
import com.boniu.account.repository.entity.AccountMainOpenEntity;
import com.boniu.account.repository.entity.AccountOpenPartnerEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountOpenService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AccountOpenServiceImpl
 * @Description
 * @Author HanXin
 * @Date 2020-06-11
 */
@Service
public class AccountOpenServiceImpl implements AccountOpenService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountMainMapper accountMainMapper;

    @Resource
    private AccountMainOpenMapper accountMainOpenMapper;

    @Resource
    private AccountOpenPartnerMapper accountOpenPartnerMapper;

    @Override
    public OpenAccessTokenVO getAccessToken(OpenGetAccessTokenRequest request) {
        //置入查询条件
        AccountOpenPartnerEntity accountOpenPartnerEntityQuery = new AccountOpenPartnerEntity();
        accountOpenPartnerEntityQuery.setAppId(request.getAppId());
        accountOpenPartnerEntityQuery.setMasterSecret(request.getMasterSecret());
        accountOpenPartnerEntityQuery.setState(AccountOpenPartnerStateEnum.NORMAL.getCode());
        //获取查询结果
        AccountOpenPartnerEntity accountOpenPartnerEntity = accountOpenPartnerMapper.selectBy(accountOpenPartnerEntityQuery);
        if (null == accountOpenPartnerEntity) {
            logger.error("#1[获取授权凭证]-[数据无效]-entity={}", accountOpenPartnerEntityQuery.toString());
            throw new BaseException(AccountErrorEnum.SECRET_IS_EXCEPTION.getErrorCode());
        }

        //判断授权凭证是否过期，未过期则返回原有的，过期则返回新的
        Date tokenExpireTime = accountOpenPartnerEntity.getTokenExpireTime();
        String accessToken = accountOpenPartnerEntity.getAccessToken();
        //授权凭证过期时间小于当前时间，则表示授权凭证已过期
        if (tokenExpireTime.before(new Date())) {
            accessToken = StringUtil.getRandomStringByLength(32);
        }
        //刷新授权凭证过期时间
        tokenExpireTime = DateUtil.getDiffDay(new Date(), 7);

        //更新数据
        AccountOpenPartnerEntity accountOpenPartnerEntityUpdate = new AccountOpenPartnerEntity();
        accountOpenPartnerEntityUpdate.setPartnerId(accountOpenPartnerEntity.getPartnerId());
        accountOpenPartnerEntityUpdate.setAccessToken(accessToken);
        accountOpenPartnerEntityUpdate.setTokenExpireTime(tokenExpireTime);
        accountOpenPartnerEntityUpdate.setUpdateTime(new Date());
        int updateNum = accountOpenPartnerMapper.update(accountOpenPartnerEntityUpdate);
        if (updateNum != 1) {
            logger.error("#1[获取授权凭证]-[更新数据失败]-entity={}", accountOpenPartnerEntityUpdate.toString());
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        //返回结果
        OpenAccessTokenVO openAccessTokenVO = new OpenAccessTokenVO();
        openAccessTokenVO.setAccessToken(accessToken);
        openAccessTokenVO.setTokenExpireTime(DateUtil.getDateString(tokenExpireTime, DateUtil.DATE_ANT_TIME_S));
        return openAccessTokenVO;
    }

    @Override
    public OpenAuthorizedLoginVO authorizedLogin(OpenAuthorizedLoginRequest request) {
        //置入查询条件
        AccountOpenPartnerEntity accountOpenPartnerEntityQuery = new AccountOpenPartnerEntity();
        accountOpenPartnerEntityQuery.setAccessToken(request.getAccessToken());
        accountOpenPartnerEntityQuery.setState(AccountOpenPartnerStateEnum.NORMAL.getCode());
        //获取查询结果
        AccountOpenPartnerEntity accountOpenPartnerEntity = accountOpenPartnerMapper.selectBy(accountOpenPartnerEntityQuery);
        if (null == accountOpenPartnerEntity) {
            logger.error("#1[授权登录]-[授权凭证不存在或无效]-entity={}", accountOpenPartnerEntityQuery.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXCEPTION.getErrorCode());
        }
        //验证授权凭证是否过期
        Date tokenExpireTime = accountOpenPartnerEntity.getTokenExpireTime();
        //授权凭证过期时间小于当前时间，则表示授权凭证已过期
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[授权登录]-[授权凭证已过期]-entity={}", accountOpenPartnerEntity.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXPIRED.getErrorCode());
        }
        //获取授权登录账户信息
        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setMobile(request.getMobile());
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        //主账户不存在，则新建主账户以及开放平台账户
        AccountMainOpenEntity accountMainOpenEntity = new AccountMainOpenEntity();
        if (null == accountMainEntity) {
            //创建主账户
            accountMainEntity = new AccountMainEntity();
            accountMainEntity.setAccountMainId(IDUtils.createID());
            accountMainEntity.setMobile(request.getMobile());
            accountMainEntity.setTotalScore(0);
            accountMainEntity.setTotalScore(0);
            accountMainEntity.setCreateTime(new Date());
            accountMainMapper.save(accountMainEntity);
            //创建开放平台账户
            accountMainOpenEntity.setOpenId(IDUtils.createID());
            accountMainOpenEntity.setAccountMainId(accountMainEntity.getAccountMainId());
            accountMainOpenEntity.setPartnerId(accountOpenPartnerEntity.getPartnerId());
            accountMainOpenEntity.setMobile(request.getMobile());
            accountMainOpenEntity.setTotalRevenue(BigDecimal.ZERO);
            accountMainOpenEntity.setRemainBalance(BigDecimal.ZERO);
            accountMainOpenEntity.setFreezeBalance(BigDecimal.ZERO);
            accountMainOpenEntity.setCreateTime(new Date());
            accountMainOpenMapper.save(accountMainOpenEntity);
        } else {
            //查询是否存在开放平台账户
            AccountMainOpenEntity accountMainOpenEntityQuery = new AccountMainOpenEntity();
            accountMainOpenEntityQuery.setAccountMainId(accountMainEntity.getAccountMainId());
            accountMainOpenEntityQuery.setPartnerId(accountOpenPartnerEntity.getPartnerId());
            accountMainOpenEntity = accountMainOpenMapper.selectBy(accountMainOpenEntityQuery);
            if (null == accountMainOpenEntity) {
                accountMainOpenEntity = new AccountMainOpenEntity();
                accountMainOpenEntity.setOpenId(IDUtils.createID());
                accountMainOpenEntity.setAccountMainId(accountMainEntity.getAccountMainId());
                accountMainOpenEntity.setPartnerId(accountOpenPartnerEntity.getPartnerId());
                accountMainOpenEntity.setMobile(request.getMobile());
                accountMainOpenEntity.setTotalRevenue(BigDecimal.ZERO);
                accountMainOpenEntity.setRemainBalance(BigDecimal.ZERO);
                accountMainOpenEntity.setFreezeBalance(BigDecimal.ZERO);
                accountMainOpenEntity.setCreateTime(new Date());
                accountMainOpenMapper.save(accountMainOpenEntity);
            }
        }

        //返回结果
        OpenAuthorizedLoginVO openAuthorizedLoginVO = new OpenAuthorizedLoginVO();
        openAuthorizedLoginVO.setMobile(accountMainOpenEntity.getMobile());
        openAuthorizedLoginVO.setOpenId(accountMainOpenEntity.getOpenId());
        return openAuthorizedLoginVO;
    }

    @Override
    public void updateOpenAccount(OpenUpdateAccountRequest request) {
        //置入查询条件
        AccountOpenPartnerEntity accountOpenPartnerEntityQuery = new AccountOpenPartnerEntity();
        accountOpenPartnerEntityQuery.setAccessToken(request.getAccessToken());
        accountOpenPartnerEntityQuery.setState(AccountOpenPartnerStateEnum.NORMAL.getCode());
        //获取查询结果
        AccountOpenPartnerEntity accountOpenPartnerEntity = accountOpenPartnerMapper.selectBy(accountOpenPartnerEntityQuery);
        if (null == accountOpenPartnerEntity) {
            logger.error("#1[授权登录]-[授权凭证不存在或无效]-entity={}", accountOpenPartnerEntityQuery.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXCEPTION.getErrorCode());
        }
        //验证授权凭证是否过期
        Date tokenExpireTime = accountOpenPartnerEntity.getTokenExpireTime();
        //授权凭证过期时间小于当前时间，则表示授权凭证已过期
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[授权登录]-[授权凭证已过期]-entity={}", accountOpenPartnerEntity.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXPIRED.getErrorCode());
        }
        AccountMainOpenEntity accountMainOpenEntityUpdate = new AccountMainOpenEntity();
        accountMainOpenEntityUpdate.setOpenId(request.getOpenId());
        accountMainOpenEntityUpdate.setTotalRevenue(request.getTotalRevenue());
        accountMainOpenEntityUpdate.setRemainBalance(request.getRemainBalance());
        accountMainOpenEntityUpdate.setFreezeBalance(request.getFreezeBalance());
        accountMainOpenEntityUpdate.setReceivedAccount(request.getReceivedAccount());
        accountMainOpenEntityUpdate.setRealName(request.getRealName());
        int updateNum = accountMainOpenMapper.update(accountMainOpenEntityUpdate);
        if (updateNum != 1) {
            logger.error("#1[更新开放平台账户数据]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }

    @Override
    public OpenAccountVO getOpenAccountInfo(OpenAccountGetRequest request) {
        //置入查询条件
        AccountOpenPartnerEntity accountOpenPartnerEntityQuery = new AccountOpenPartnerEntity();
        accountOpenPartnerEntityQuery.setAccessToken(request.getAccessToken());
        accountOpenPartnerEntityQuery.setState(AccountOpenPartnerStateEnum.NORMAL.getCode());
        //获取查询结果
        AccountOpenPartnerEntity accountOpenPartnerEntity = accountOpenPartnerMapper.selectBy(accountOpenPartnerEntityQuery);
        if (null == accountOpenPartnerEntity) {
            logger.error("#1[授权登录]-[授权凭证不存在或无效]-entity={}", accountOpenPartnerEntityQuery.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXCEPTION.getErrorCode());
        }
        //验证授权凭证是否过期
        Date tokenExpireTime = accountOpenPartnerEntity.getTokenExpireTime();
        //授权凭证过期时间小于当前时间，则表示授权凭证已过期
        if (tokenExpireTime.before(new Date())) {
            logger.error("#1[授权登录]-[授权凭证已过期]-entity={}", accountOpenPartnerEntity.toString());
            throw new BaseException(AccountErrorEnum.ACCESS_TOKEN_IS_EXPIRED.getErrorCode());
        }
        AccountMainOpenEntity accountMainOpenEntityQuery = new AccountMainOpenEntity();
        accountMainOpenEntityQuery.setOpenId(request.getOpenId());
        accountMainOpenEntityQuery.setReceivedAccount(request.getReceivedAccount());
        AccountMainOpenEntity accountMainOpenEntity = accountMainOpenMapper.selectBy(accountMainOpenEntityQuery);
        if (null == accountMainOpenEntity) {
            logger.error("#1[获取开放平台账户数据]-[数据不存在]-request={}", request);
            throw new BaseException(AccountErrorEnum.OPEN_ACCOUNT_IS_NOT_EXIST.getErrorCode());
        }
        OpenAccountVO openAccountVO = new OpenAccountVO();
        openAccountVO.setOpenId(accountMainOpenEntity.getOpenId());
        openAccountVO.setTotalRevenue(accountMainOpenEntity.getTotalRevenue());
        openAccountVO.setRemainBalance(accountMainOpenEntity.getRemainBalance());
        openAccountVO.setFreezeBalance(accountMainOpenEntity.getFreezeBalance());
        openAccountVO.setReceivedAccount(accountMainOpenEntity.getReceivedAccount());
        openAccountVO.setRealName(accountMainOpenEntity.getRealName());
        return openAccountVO;
    }
}
