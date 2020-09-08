package com.boniu.account.api;

import com.boniu.account.api.request.QueryAccountMainDetailRequest;
import com.boniu.account.api.request.UpdateAccountMainRequest;
import com.boniu.account.api.vo.MainAccountVO;
import com.boniu.base.utile.message.BaseResponse;

/**
 * @ClassName AccountMainApi
 * @Author HanXin 主账户相关接口
 * @Date 2020-06-05
 */

public interface AccountMainApi {
    /**
     * 获取主账户相关信息
     * @return
     */
    BaseResponse<MainAccountVO> getDetail(QueryAccountMainDetailRequest request);

    /**
     * 更新主账户信息
     * @param request
     * @return
     */
    BaseResponse<Boolean> update(UpdateAccountMainRequest request);
}
