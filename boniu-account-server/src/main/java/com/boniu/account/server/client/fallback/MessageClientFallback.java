package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.MessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName MessageClientFallBack
 * @Description
 * @Author HanXin
 * @Date 2019-12-05
 */
@Service
public class MessageClientFallback implements MessageClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
