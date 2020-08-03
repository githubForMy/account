package com.boniu.account.server.service.impl;

import com.boniu.account.api.request.MainAccountRequest;
import com.boniu.account.api.request.SaveMainAccountRequest;
import com.boniu.account.api.vo.MainAccountVO;
import com.boniu.account.repository.api.AccountMainMapper;
import com.boniu.account.repository.entity.AccountMainEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountMainService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.tool.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AccountMainServiceImpl
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

@Service
public class AccountMainServiceImpl implements AccountMainService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountMainMapper accountMainMapper;

    @Override
    public void saveMainAccount(SaveMainAccountRequest request) {
        AccountMainEntity accountMainEntity = new AccountMainEntity();
        accountMainEntity.setAccountMainId(IDUtils.createID());
        accountMainEntity.setMobile(request.getMobile());
        accountMainEntity.setCreateTime(new Date());
        int saveNum = accountMainMapper.save(accountMainEntity);
        if (saveNum != 1) {
            logger.error("#1[保存主账户信息]-[插入数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }

    @Override
    public void updateMainAccount(MainAccountRequest request) {
        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setAccountMainId(request.getMainAccountId());
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        if (null == accountMainEntity) {
            logger.error("#1[更新主账户信息]-[信息不存在]-request={}", request);
            throw new BaseException(AccountErrorEnum.ACCOUNT_NOT_EXIST.getErrorCode());
        }

        AccountMainEntity accountMainEntityUpdate = new AccountMainEntity();
        accountMainEntityUpdate.setAccountMainId(request.getMainAccountId());
        accountMainEntityUpdate.setUpdateTime(new Date());
        int updateNum = accountMainMapper.update(accountMainEntityUpdate);
        if (updateNum != 1) {
            logger.error("#1[更新主账户信息]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }

    @Override
    public MainAccountVO getMainAccountInfo(MainAccountRequest request) {
        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setAccountMainId(request.getMainAccountId());
        accountMainEntityQuery.setMobile(request.getMobile());
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        //若手机号不存在，则创建新主账号信息
        if (null == accountMainEntity) {
            accountMainEntity = new AccountMainEntity();
            accountMainEntity.setAccountMainId(IDUtils.createID());
            accountMainEntity.setMobile(request.getMobile());
            accountMainEntity.setCreateTime(new Date());
            int saveNum = accountMainMapper.save(accountMainEntity);
            if (saveNum != 1) {
                logger.error("#1[获取主账户相关信息]-[插入数据失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        }

        MainAccountVO mainAccountVO = new MainAccountVO();
        mainAccountVO.setMainAccountId(accountMainEntity.getAccountMainId());
        mainAccountVO.setMobile(accountMainEntity.getMobile());
        return mainAccountVO;
    }
}
