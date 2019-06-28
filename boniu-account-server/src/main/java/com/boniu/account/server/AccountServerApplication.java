package com.boniu.account.server;

import com.boniu.base.cache.conf.EnableBoniuCache;
import com.boniu.common.help.conf.EnableCommonHelp;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by ZZF on 18/06/12.
 */
@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients
@EnableBoniuCache
@EnableCommonHelp
public class AccountServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountServerApplication.class).web(true).run(args);
    }
}
