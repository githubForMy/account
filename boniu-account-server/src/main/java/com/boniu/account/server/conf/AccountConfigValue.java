package com.boniu.account.server.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by ZZF on 18/06/12.
 */
@ConfigurationProperties
@Component
@RefreshScope
public class AccountConfigValue {

    @Value("${spring.application.name}")
    private String systemName;

    public String getSystemName() {
        return systemName;
    }

}
