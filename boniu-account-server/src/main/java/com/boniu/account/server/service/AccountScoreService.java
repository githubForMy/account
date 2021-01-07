package com.boniu.account.server.service;

import com.boniu.account.api.request.AddAccountScoreRequest;
import com.boniu.account.api.request.UpdateAccountScoreRequest;
import com.boniu.account.api.result.AccountScoreResult;
import com.boniu.base.utile.message.BaseRequest;

/**
 * @description:
 * @author: FengXian
 * @create: 2021-01-06 10:45
 **/
public interface AccountScoreService {
    /**
     * 新增用户积分信息
     *
     * @param request
     * @return
     */
    Boolean addAccountScore(AddAccountScoreRequest request);

    /**
     * 更新用户积分信息
     *
     * @param request
     * @return
     */
    Boolean updateAccountScore(UpdateAccountScoreRequest request);

    /**
     * 查询用户积分信息
     *
     * @param baseRequest
     * @return
     */
    AccountScoreResult getAccountScore(BaseRequest baseRequest);
}
