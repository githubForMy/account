package com.boniu.account.server.service;

import com.boniu.account.api.vo.AccountCancelApplyVO;
import com.boniu.base.utile.message.BaseRequest;

/**
 * @Description AccountCancelApplyService
 * @Author hanxin
 * @Date 2020/11/5
 */

public interface AccountCancelApplyService {
    /**
     * 获取注销信息
     * @return
     */
    AccountCancelApplyVO getApplyInfo(BaseRequest request);

    /**
     * 申请账号注销
     * @param request
     */
    void apply(BaseRequest request);

    /**
     * 取消账号注销
     * @param request
     */
    void cancel(BaseRequest request);
}
