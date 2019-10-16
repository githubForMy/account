package com.boniu.account.server.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ZZF on 2018/6/12.
 */
@Configurable
@EnableFeignClients
@ComponentScan("com.boniu.account.server.client")
public class ClientConf {
}
