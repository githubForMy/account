package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.MarketingClientFallback;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.marketing.api.request.QueryProductRequest;
import com.boniu.marketing.api.vo.ProductDetailVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @InterfaceName MarketingClient
 * @Author FengXian
 * @Date 2019-12-04
 */

@FeignClient(serviceId = "boniu-marketing", fallback = MarketingClientFallback.class)
public interface MarketingClient {

    @RequestMapping(value = "/marketing/product/getInfo", method = RequestMethod.POST)
    BaseResponse<ProductDetailVO> getInfo(@RequestBody QueryProductRequest request);
}
