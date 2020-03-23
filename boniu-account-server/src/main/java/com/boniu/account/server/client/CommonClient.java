package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.CommonClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ZZF on 18/06/12.
 */
@FeignClient(serviceId = "boniu-common", fallback = CommonClientFallback.class)
public interface CommonClient {

}
