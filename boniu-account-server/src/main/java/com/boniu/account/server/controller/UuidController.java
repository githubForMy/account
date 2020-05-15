package com.boniu.account.server.controller;

import com.boniu.account.api.UuidApi;
import com.boniu.account.api.request.AddUuidRequest;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.UuidService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UuidController
 * @Description
 * @Author HanXin
 * @Date 2020-05-15
 */
@RestController
@RequestMapping("/account/uuid")
public class UuidController implements UuidApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UuidService uuidService;

    /**
     * 新增设备信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "新增设备信息", notes = "com.boniu.marketing.api.UuidApi.add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse add(@RequestBody AddUuidRequest request) {
        logger.info("#1[新增设备信息]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getPlatform())
                || StringUtil.isBlank(request.getUuid())) {
            logger.error("#1[新增设备信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = uuidService.addUuid(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[新增设备信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[新增设备信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.ADD_UUID_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
