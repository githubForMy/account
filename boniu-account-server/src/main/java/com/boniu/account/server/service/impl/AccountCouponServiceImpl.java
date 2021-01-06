package com.boniu.account.server.service.impl;

import com.boniu.account.api.enums.AccountCouponStatusEnum;
import com.boniu.account.api.request.AddAccountCouponRequest;
import com.boniu.account.api.request.GetAccountCouponRequest;
import com.boniu.account.api.request.UpdateAccountCouponRequest;
import com.boniu.account.api.vo.AccountCouponVo;
import com.boniu.account.repository.api.AccountCouponMapper;
import com.boniu.account.repository.entity.AccountCouponEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountCouponService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.tool.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author hanxin
 * @Date 2021/1/4
 */
@Service
public class AccountCouponServiceImpl implements AccountCouponService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AccountCouponMapper accountCouponMapper;

    /**
     * 获取用户优惠券信息
     * @param request
     * @return
     */
    @Override
    public AccountCouponVo getAccountCouponDetail(GetAccountCouponRequest request) {
        AccountCouponVo accountCouponVo = null;
        AccountCouponEntity accountCouponEntityQuery = new AccountCouponEntity();
        accountCouponEntityQuery.setAccountCouponId(request.getAccountCouponId());
        accountCouponEntityQuery.setCouponId(request.getCouponId());
        accountCouponEntityQuery.setAccountId(request.getAccountId());
        accountCouponEntityQuery.setType(request.getType());
        accountCouponEntityQuery.setStatus(request.getStatus());
        accountCouponEntityQuery.setSource(request.getSource());
        AccountCouponEntity accountCouponEntity = accountCouponMapper.selectBy(accountCouponEntityQuery);
        if (null != accountCouponEntity) {
            accountCouponVo = new AccountCouponVo();
            BeanUtils.copyProperties(accountCouponEntity, accountCouponVo);
        }
        return accountCouponVo;
    }

    /**
     * 新增优惠券信息
     * @param request
     * @return
     */
    @Override
    public void addAccountCoupon(AddAccountCouponRequest request) {
        AccountCouponEntity accountCouponEntitySave = new AccountCouponEntity();
        accountCouponEntitySave.setAccountCouponId(IDUtils.createID());
        accountCouponEntitySave.setCouponId(request.getCouponId());
        accountCouponEntitySave.setAccountId(request.getAccountId());
        accountCouponEntitySave.setType(request.getType());
        accountCouponEntitySave.setAmount(request.getAmount());
        accountCouponEntitySave.setStatus(AccountCouponStatusEnum.USABLE.getCode());
        accountCouponEntitySave.setSource(request.getSource());
        int saveNum = accountCouponMapper.save(accountCouponEntitySave);
        if (saveNum != 1) {
            logger.error("#1[新增优惠券信息]-[插入数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }

    }

    /**
     * 更新优惠券信息
     * @param request
     * @return
     */
    @Override
    public void updateAccountCoupon(UpdateAccountCouponRequest request) {
        AccountCouponEntity accountCouponEntityUpdate = new AccountCouponEntity();
        accountCouponEntityUpdate.setAccountCouponId(request.getAccountCouponId());
        accountCouponEntityUpdate.setStatus(request.getStatus());
        accountCouponEntityUpdate.setUpdateTime(new Date());
        int updateNum = accountCouponMapper.update(accountCouponEntityUpdate);
        if (updateNum != 1) {
            logger.error("#1[更新优惠券信息]-[更新数据失败]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }
}
