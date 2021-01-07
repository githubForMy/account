package com.boniu.account.api;

import com.boniu.account.api.request.AddAccountScoreRequest;
import com.boniu.account.api.request.UpdateAccountScoreRequest;
import com.boniu.account.api.result.AccountScoreResult;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @description:
 * @author: FengXian
 * @create: 2021-01-06 11:09
 **/
public interface AccountScoreApi {
    /**
     * 新增用户信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> add(AddAccountScoreRequest request);

    /**
     * 更新用户信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> update(UpdateAccountScoreRequest request);

    /**
     * 查询用户积分信息
     *
     * @param request
     * @return
     */
    BaseResponse<AccountScoreResult> getAccountScore(BaseRequest request);

}
