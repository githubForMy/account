package com.boniu.account.api;

import com.boniu.account.api.request.AddAccountCouponRequest;
import com.boniu.account.api.request.GetAccountCouponRequest;
import com.boniu.account.api.request.UpdateAccountCouponRequest;
import com.boniu.account.api.vo.AccountCouponVo;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @Description
 * @Author hanxin
 * @Date 2021/1/4
 */

public interface AccountCouponApi {
    /**
     * 获取用户优惠券信息
     * @param request
     * @return
     */
    BaseResponse<AccountCouponVo> getDetail(GetAccountCouponRequest request);

    /**
     * 新增优惠券信息
     * @param request
     * @return
     */
    BaseResponse<Boolean> add(AddAccountCouponRequest request);

    /**
     * 更新优惠券信息
     * @param request
     * @return
     */
    BaseResponse<Boolean> update(UpdateAccountCouponRequest request);
}
