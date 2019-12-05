package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.CommonClientFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @InterfaceName MessageClient
 * @Description
 * @Author HanXin
 * @Date 2019-12-05
 */

@FeignClient(serviceId = "boniu-message", fallback = CommonClientFallBack.class)
public interface MessageClient {
}
