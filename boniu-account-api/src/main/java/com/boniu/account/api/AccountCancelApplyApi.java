package com.boniu.account.api;

import com.boniu.account.api.vo.AccountCancelApplyVO;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @Description 账户注销
 * @Author hanxin
 * @Date 2020/11/5
 */

public interface AccountCancelApplyApi {
    /**
     * 获取注销信息
     * @param request
     * @return
     */
    BaseResponse<AccountCancelApplyVO> getApplyInfo(BaseRequest request);

    /**
     * 申请注销
     * @param request
     * @return
     */
    BaseResponse<Boolean> apply(BaseRequest request);

    /**
     * 取消申请注销
     * @param request
     * @return
     */
    BaseResponse<Boolean> cancel(BaseRequest request);
}
