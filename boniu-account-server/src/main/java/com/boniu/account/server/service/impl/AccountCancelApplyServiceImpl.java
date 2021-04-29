package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountCancelApplyStatusEnum;
import com.boniu.account.api.vo.AccountCancelApplyVO;
import com.boniu.account.repository.api.AccountCancelApplyMapper;
import com.boniu.account.repository.api.AccountMapper;
import com.boniu.account.repository.entity.AccountCancelApplyEntity;
import com.boniu.account.repository.entity.AccountEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountCancelApplyService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Resource
    private AccountMapper accountMapper;

    /**
     * 获取注销信息
     *
     * @param request
     * @return
     */
    @Override
    public AccountCancelApplyVO getApplyInfo(BaseRequest request) {
        AccountEntity accountEntity = accountMapper.selectByAccountIdAndAppName(request.getAccountId(), request.getAppName());
        if (null == accountEntity) {
            logger.error("#1[获取注销信息]-[账户信息异常]-request={}", request);
            throw new BaseException(AccountErrorEnum.ACCOUNT_IS_EXCEPTION.getErrorCode());
        }

        AccountCancelApplyVO accountCancelApplyVO = new AccountCancelApplyVO();
        AccountCancelApplyEntity accountCancelApplyEntity = accountCancelApplyMapper.selectByAccountId(request.getAccountId());
        if (null != accountCancelApplyEntity) {
            accountCancelApplyVO.setMobile(accountEntity.getMobile());
            accountCancelApplyVO.setConsumeDays(7);
            accountCancelApplyVO.setApplyTime(DateUtil.getDateString(accountCancelApplyEntity.getApplyTime(), DateUtil.DATE_ANT_TIME_S));
            accountCancelApplyVO.setCanncelTime(null != accountCancelApplyEntity.getCancelTime() ? DateUtil.getDateString(accountCancelApplyEntity.getCancelTime(), DateUtil.DATE_ANT_TIME_S) : null);
            accountCancelApplyVO.setStatus(accountCancelApplyEntity.getStatus());
            return accountCancelApplyVO;
        }

        accountCancelApplyVO.setMobile(accountEntity.getMobile());
        accountCancelApplyVO.setConsumeDays(7);
        accountCancelApplyVO.setStatus(AccountCancelApplyStatusEnum.INIT.getCode());
        return accountCancelApplyVO;
    }

    /**
     * 申请账号注销
     * @param request
     */
    @Override
    public void apply(BaseRequest request) {
        AccountCancelApplyEntity accountCancelApplyEntity = accountCancelApplyMapper.selectByAccountId(request.getAccountId());
        if (null != accountCancelApplyEntity) {
            Date applyTime = accountCancelApplyEntity.getApplyTime();
            int diffDay = DateUtil.getDiffDays(applyTime, new Date());
            if (diffDay <= 0) {
                logger.error("#1[申请账号注销]-[24小时内已提交注销申请，请稍候重试]-request={}", request);
                throw new BaseException(AccountErrorEnum.ALREADY_APPLY.getErrorCode());
            }
            if (StringUtil.equals(accountCancelApplyEntity.getStatus(), AccountCancelApplyStatusEnum.AUDITING.getCode())) {
                logger.error("#1[申请账号注销]-[注销申请已在审核中]-request={}", request);
                throw new BaseException(AccountErrorEnum.CANCEL_ACCOUNT_AUDITING.getErrorCode());
            }

            AccountCancelApplyEntity accountCancelApplyUpdate = new AccountCancelApplyEntity();
            accountCancelApplyUpdate.setAccountId(request.getAccountId());
            accountCancelApplyUpdate.setApplyTime(new Date());
            accountCancelApplyUpdate.setStatus(AccountCancelApplyStatusEnum.AUDITING.getCode());
            accountCancelApplyUpdate.setUpdateTime(new Date());
            int saveNum = accountCancelApplyMapper.update(accountCancelApplyUpdate);
            if (saveNum != 1) {
                logger.error("#1[申请账号注销]-[更新数据失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        } else {
            //提交申请
            AccountCancelApplyEntity accountCancelApplySave = new AccountCancelApplyEntity();
            accountCancelApplySave.setApplyId(IDUtils.createID());
            accountCancelApplySave.setAccountId(request.getAccountId());
            accountCancelApplySave.setAppName(request.getAppName());
            accountCancelApplySave.setApplyTime(new Date());
            accountCancelApplySave.setStatus(AccountCancelApplyStatusEnum.AUDITING.getCode());
            accountCancelApplySave.setCreateTime(new Date());
            int saveNum = accountCancelApplyMapper.save(accountCancelApplySave);
            if (saveNum != 1) {
                logger.error("#1[申请账号注销]-[插入数据失败]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
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
        accountCancelApplyEntity.setStatus(AccountCancelApplyStatusEnum.CANCEL_APPLY.getCode());
        accountCancelApplyEntity.setUpdateTime(new Date());
        int saveNum = accountCancelApplyMapper.update(accountCancelApplyEntity);
        if (saveNum != 1) {
            logger.error("#1[取消账号注销]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }

    /**
     * 注销账号
     */
    @Override
    public void canncelAccount() {
        List<AccountCancelApplyEntity> accountCancelApplyEntities = accountCancelApplyMapper.selectAuditList();
        List<String> cancelAccountIds = new ArrayList<>();
        //获取已注销申请的账户id
        if (CollectionUtils.isNotEmpty(accountCancelApplyEntities)) {
            for (AccountCancelApplyEntity accountCancelApplyEntity : accountCancelApplyEntities) {
                String accountId = accountCancelApplyEntity.getAccountId();
                cancelAccountIds.add(accountId);
            }
        }
        //通过accountIds更新用户状态为注销
        if (CollectionUtils.isNotEmpty(cancelAccountIds)) {
            accountMapper.updateCancelStatusByAccountIds(cancelAccountIds);
        }

        //更新注销申请状态为注销完成
        if (CollectionUtils.isNotEmpty(cancelAccountIds)) {
            accountCancelApplyMapper.updateCancelStatusByAccountIds(cancelAccountIds);
        }
    }
}
