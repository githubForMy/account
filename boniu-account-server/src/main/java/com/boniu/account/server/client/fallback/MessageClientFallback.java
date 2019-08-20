package com.boniu.account.server.client.fallback;

import com.boniu.account.server.client.MessageClient;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.message.api.request.CheckVerifyCodeRequest;
import com.boniu.message.api.request.SendVerifyCodeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName MessageClientFallback
 * @Description
 * @Author HanXin
 * @Date 2019-07-02
 */

@Component
public class MessageClientFallback implements MessageClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 验证码短信发送
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse<Boolean> sendVerifyCode(SendVerifyCodeRequest request) {
        logger.error("#3[调用消息系统]-[失败]-request={}", request);
        BaseResponse<Boolean> response = new BaseResponse<>();
        return response;
    }

    /**
     * 校验短信验证码
     *
     * @param request
     * @return
     */
    @Override
    public BaseResponse<Boolean> checkVerifyCode(CheckVerifyCodeRequest request) {
        logger.error("#3[调用消息系统]-[失败]-request={}", request);
        BaseResponse<Boolean> response = new BaseResponse<>();
        return response;
    }
}
