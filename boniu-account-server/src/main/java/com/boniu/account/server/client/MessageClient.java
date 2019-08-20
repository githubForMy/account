package com.boniu.account.server.client;

import com.boniu.account.server.client.fallback.MessageClientFallback;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.message.api.request.CheckVerifyCodeRequest;
import com.boniu.message.api.request.SendVerifyCodeRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName MessageClient
 * @Description
 * @Author HanXin
 * @Date 2019-07-02
 */

@FeignClient(value = "boniu-message", fallback = MessageClientFallback.class)
public interface MessageClient {
    /**
     * 验证码短信发送
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/message/sendVerifyCode", method = RequestMethod.POST)
    BaseResponse<Boolean> sendVerifyCode(SendVerifyCodeRequest request);

    /**
     * 校验短信验证码
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/message/checkVerifyCode", method = RequestMethod.POST)
    BaseResponse<Boolean> checkVerifyCode(CheckVerifyCodeRequest request);
}
