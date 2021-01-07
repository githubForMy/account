package com.boniu.account.server.service.impl;

import com.boniu.account.api.request.AddAccountScoreRequest;
import com.boniu.account.api.request.UpdateAccountScoreRequest;
import com.boniu.account.api.result.AccountScoreResult;
import com.boniu.account.repository.api.AccountScoreMapper;
import com.boniu.account.repository.entity.AccountScoreEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountScoreService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户积分信息
 * @author: FengXian
 * @create: 2021-01-06 10:44
 **/
@Service
public class AccountScoreServiceImpl implements AccountScoreService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountScoreMapper accountScoreMapper;

    /**
     * 新增用户积分信息
     *
     * @param request
     * @return
     */
    @Override
    public Boolean addAccountScore(AddAccountScoreRequest request) {
        AccountScoreEntity accountScoreEntity = new AccountScoreEntity();
        accountScoreEntity.setAccountId(request.getAccountId());
        accountScoreEntity.setAppName(request.getAppName());
        accountScoreEntity.setTotalScore(0);
        accountScoreEntity.setRemainScore(0);
        accountScoreEntity.setTimes(0);
        int num = accountScoreMapper.save(accountScoreEntity);

        if (num != 1) {
            logger.error("#1[新增用户积分信息]-[插入数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        return true;
    }

    /**
     * 更新用户积分记录
     *
     * @param request
     * @return
     */
    @Override
    public Boolean updateAccountScore(UpdateAccountScoreRequest request) {
        AccountScoreEntity accountScoreEntity = new AccountScoreEntity();
        accountScoreEntity.setAccountId(request.getAccountId());
        accountScoreEntity.setTotalScore(request.getTotalScore());
        accountScoreEntity.setRemainScore(request.getRemainScore());
        accountScoreEntity.setTimes(request.getTimes());
        int num = accountScoreMapper.update(accountScoreEntity);

        if (num != 1) {
            logger.error("#1[更新用户积分信息]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

        return true;
    }

    /**
     * 查询用户积分信息
     *
     * @param
     * @return
     */
    @Override
    public AccountScoreResult getAccountScore(BaseRequest request) {
        AccountScoreResult result = new AccountScoreResult();

        AccountScoreEntity accountScoreEntity = accountScoreMapper.selectByAccountId(request.getAccountId());

        if (accountScoreEntity == null) {
            logger.error("#1[查询用户积分信息]-[查询数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.GET_ACCOUNT_SCORE_FAILURE.getErrorCode());
        }
        BeanUtils.copyProperties(accountScoreEntity, result);

        return result;
    }
}
