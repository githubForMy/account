package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.PayClientFallback;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.pay.api.request.QueryOrderByUuidRequest;
import com.boniu.pay.api.request.UpdateOrderRequest;
import com.boniu.pay.api.vo.OrderDetailVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @InterfaceName PayClient
 * @Description
 * @Author HanXin
 * @Date 2020-03-23
 */

@FeignClient(serviceId = "boniu-pay", fallback = PayClientFallback.class)
public interface PayClient {

    @RequestMapping(value = "/order/queryByUuid", method = RequestMethod.POST)
    BaseResponse<OrderDetailVO> queryByUuid(@RequestBody QueryOrderByUuidRequest request);

    @RequestMapping(value = "/order/updateOrder", method = RequestMethod.POST)
    BaseResponse<Boolean> updateOrder(@RequestBody UpdateOrderRequest request);
}