package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.PayClientFallback;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.pay.api.request.QueryOrderByUuidRequest;
import com.boniu.pay.api.request.UpdateAccountIdByUuidRequest;
import com.boniu.pay.api.request.UpdateOrderRequest;
import com.boniu.pay.api.vo.OrderDetailVO;
import com.boniu.pay.api.vo.PayProductVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @InterfaceName PayClient
 * @Description
 * @Author HanXin
 * @Date 2020-03-23
 */

@FeignClient(serviceId = "boniu-pay", fallback = PayClientFallback.class)
public interface PayClient {

    @RequestMapping(value = "/order/queryByUuid", method = RequestMethod.POST)
    BaseResponse<List<OrderDetailVO>> queryByUuid(@RequestBody QueryOrderByUuidRequest request);

    @RequestMapping(value = "/order/updateOrder", method = RequestMethod.POST)
    BaseResponse<Boolean> updateOrder(@RequestBody UpdateOrderRequest request);

    /**
     * 通过uuid批量更新accountId
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/appleOrder/batchUpdateAccountIdByUuid", method = RequestMethod.POST)
    BaseResponse<Boolean> batchUpdateAccountIdByUuid(UpdateAccountIdByUuidRequest request);

    /**
     * 获取应用支付产品详细信息
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/pay/product/getInfo", method = RequestMethod.GET)
    BaseResponse<PayProductVo> getInfo(@RequestParam("productId") String productId);
}
