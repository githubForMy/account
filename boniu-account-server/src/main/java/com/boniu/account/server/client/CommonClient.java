package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.CommonClientFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ZZF on 18/06/12.
 */
@FeignClient(serviceId = "boniu-common", fallback = CommonClientFallBack.class)
public interface CommonClient {

}
