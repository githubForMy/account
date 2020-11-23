package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.PayClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.pay.api.request.QueryOrderByUuidRequest;
import com.boniu.pay.api.request.QueryOrderDetailRequest;
import com.boniu.pay.api.request.UpdateAccountIdByUuidRequest;
import com.boniu.pay.api.request.UpdateOrderRequest;
import com.boniu.pay.api.vo.OrderDetailVO;
import com.boniu.pay.api.vo.PayProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PayClientFallBack
 * @Description
 * @Author HanXin
 * @Date 2020-03-23
 */

@Service
public class PayClientFallback implements PayClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BaseResponse<List<OrderDetailVO>> queryByUuid(QueryOrderByUuidRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }

    @Override
    public BaseResponse<Boolean> updateOrder(UpdateOrderRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }

    @Override
    public BaseResponse<Boolean> batchUpdateAccountIdByUuid(UpdateAccountIdByUuidRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }

    @Override
    public BaseResponse<PayProductVo> getInfo(String productId) {
        logger.error("#3[调用支付系统]-[失败]-productId={}", productId);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }

    @Override
    public BaseResponse<OrderDetailVO> queryDetail(QueryOrderDetailRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_TRADE_FAIL.getErrorCode());
    }
}
