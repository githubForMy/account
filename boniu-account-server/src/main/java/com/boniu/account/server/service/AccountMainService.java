package com.boniu.account.server.service;

import com.boniu.account.api.request.QueryAccountMainDetailRequest;
import com.boniu.account.api.request.UpdateAccountMainRequest;
import com.boniu.account.api.vo.AccountMainVO;

/**
 * @ClassName AccountMainService
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

public interface AccountMainService {

    /**
     * 获取主账户相关信息
     * @param request
     * @return
     */
    AccountMainVO getAccountMainDetail(QueryAccountMainDetailRequest request);

    /**
     * 更新主账户信息
     * @param request
     */
    void updateAccountMain(UpdateAccountMainRequest request);
}
