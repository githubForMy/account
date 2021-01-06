package com.boniu.account.server.service;

import com.boniu.account.api.request.AddAccountCouponRequest;
import com.boniu.account.api.request.GetAccountCouponRequest;
import com.boniu.account.api.request.UpdateAccountCouponRequest;
import com.boniu.account.api.vo.AccountCouponVo;

/**
 * @Description
 * @Author hanxin
 * @Date 2021/1/4
 */

public interface AccountCouponService {
    /**
     * 获取用户优惠券信息
     * @param request
     * @return
     */
    AccountCouponVo getAccountCouponDetail(GetAccountCouponRequest request);

    /**
     * 新增优惠券信息
     * @param request
     * @return
     */
    void addAccountCoupon(AddAccountCouponRequest request);

    /**
     * 更新优惠券信息
     * @param request
     * @return
     */
    void updateAccountCoupon(UpdateAccountCouponRequest request);
}
