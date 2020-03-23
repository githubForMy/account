package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.PayClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.pay.api.request.QueryOrderByUuidRequest;
import com.boniu.pay.api.vo.OrderDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName PayClientFallBack
 * @Description
 * @Author HanXin
 * @Date 2020-03-23
 */

@Service
public class PayClientFallBack implements PayClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BaseResponse<OrderDetailVO> queryByUuid(QueryOrderByUuidRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }
}
