package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.CommonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by ZZF on 18/06/12.
 */
@Service
public class CommonClientFallBack implements CommonClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
