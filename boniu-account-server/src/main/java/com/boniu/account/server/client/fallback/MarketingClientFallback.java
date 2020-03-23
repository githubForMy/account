package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.MarketingClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.marketing.api.request.QueryProductRequest;
import com.boniu.marketing.api.vo.ProductDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName MarketingClientFallback
 * @Description
 * @Author HanXin
 * @Date 2020-03-23
 */

@Service
public class MarketingClientFallback implements MarketingClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BaseResponse<ProductDetailVO> getInfo(QueryProductRequest request) {
        logger.error("#3[调用支付系统]-[失败]-request={}", request);
        throw new BaseException(AccountErrorEnum.CALL_MARKETING_FAIL.getErrorCode());
    }
}
