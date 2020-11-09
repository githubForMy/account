package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountCancelApplyStatusEnum;
import com.boniu.account.api.vo.AccountCancelApplyVO;
import com.boniu.account.repository.api.AccountCancelApplyMapper;
import com.boniu.account.repository.entity.AccountCancelApplyEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountCancelApplyService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description AccountCancelApplyService实现类
 * @Author hanxin
 * @Date 2020/11/5
 */
@Service
public class AccountCancelApplyServiceImpl implements AccountCancelApplyService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountCancelApplyMapper accountCancelApplyMapper;

    /**
     * 获取注销信息
     * @param request
     * @return
     */
    @Override
    public AccountCancelApplyVO getApplyInfo(BaseRequest request) {
        AccountCancelApplyEntity accountCancelApplyEntity = accountCancelApplyMapper.selectByAccountId(request.getAccountId());
        if (null != accountCancelApplyEntity) {
            AccountCancelApplyVO accountCancelApplyVO = new AccountCancelApplyVO();
            accountCancelApplyVO.setMobile(accountCancelApplyEntity.getAccountId());
            accountCancelApplyVO.setConsumeDays(7);
            accountCancelApplyVO.setApplyTime(DateUtil.getDateString(accountCancelApplyEntity.getApplyTime(), DateUtil.DATE_ANT_TIME_S));
            accountCancelApplyVO.setCanncelTime(DateUtil.getDateString(accountCancelApplyEntity.getCancelTime(), DateUtil.DATE_ANT_TIME_S));
            accountCancelApplyVO.setStatus(accountCancelApplyEntity.getStatus());
            return accountCancelApplyVO;
        }
        return null;
    }

    /**
     * 申请账号注销
     * @param request
     */
    @Override
    public void apply(BaseRequest request) {
        AccountCancelApplyEntity accountCancelApplyEntity = new AccountCancelApplyEntity();
        accountCancelApplyEntity.setApplyId(IDUtils.createID());
        accountCancelApplyEntity.setAccountId(request.getAccountId());
        accountCancelApplyEntity.setAppName(request.getAppName());
        accountCancelApplyEntity.setApplyTime(new Date());
        accountCancelApplyEntity.setStatus(AccountCancelApplyStatusEnum.AUDITING.getCode());
        accountCancelApplyEntity.setCreateTime(new Date());
        int saveNum = accountCancelApplyMapper.save(accountCancelApplyEntity);
        if (saveNum != 1) {
            logger.error("#1[申请账号注销]-[插入数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

    }

    /**
     * 取消账号注销
     * @param request
     */
    @Override
    public void cancel(BaseRequest request) {
        AccountCancelApplyEntity accountCancelApplyEntity = new AccountCancelApplyEntity();
        accountCancelApplyEntity.setAccountId(request.getAccountId());
        accountCancelApplyEntity.setStatus(AccountCancelApplyStatusEnum.INIT.getCode());
        accountCancelApplyEntity.setUpdateTime(new Date());
        int saveNum = accountCancelApplyMapper.update(accountCancelApplyEntity);
        if (saveNum != 1) {
            logger.error("#1[取消账号注销]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }
}
