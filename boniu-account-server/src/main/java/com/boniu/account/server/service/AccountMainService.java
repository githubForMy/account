package com.boniu.account.server.service;

import com.boniu.account.api.request.MainAccountRequest;
import com.boniu.account.api.request.SaveMainAccountRequest;
import com.boniu.account.api.vo.MainAccountVO;

/**
 * @ClassName AccountMainService
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

public interface AccountMainService {
    /**
     * 保存主账户信息
     *
     * @param request
     */
    void saveMainAccount(SaveMainAccountRequest request);

    /**
     * 更新主账户信息
     *
     * @param request
     */
    void updateMainAccount(MainAccountRequest request);

    /**
     * 获取主账户相关信息
     *
     * @param request
     * @return
     */
    MainAccountVO getMainAccountInfo(MainAccountRequest request);
}
