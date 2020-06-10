package com.boniu.account.api;

import com.boniu.account.api.request.MainAccountRequest;
import com.boniu.account.api.request.SaveMainAccountRequest;
import com.boniu.account.api.vo.MainAccountVO;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @ClassName AccountMainApi
 * @Author HanXin 主账户相关接口
 * @Date 2020-06-05
 */

public interface AccountMainApi {
    /**
     * 保存主账户信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> save(SaveMainAccountRequest request);

    /**
     * 更新主账户信息
     *
     * @param request
     * @return
     */
    BaseResponse<Boolean> update(MainAccountRequest request);

    /**
     * 获取主账户相关信息
     *
     * @return
     */
    BaseResponse<MainAccountVO> getInfo(MainAccountRequest request);
}
