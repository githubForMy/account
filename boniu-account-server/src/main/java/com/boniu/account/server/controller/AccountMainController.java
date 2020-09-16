package com.boniu.account.server.controller;

import com.boniu.account.api.AccountMainApi;
import com.boniu.account.api.request.QueryAccountMainDetailRequest;
import com.boniu.account.api.request.UpdateAccountMainRequest;
import com.boniu.account.api.vo.AccountMainVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountMainService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AccountMainController
 * @Description 主账户相关接口
 * @Author HanXin
 * @Date 2020-06-05
 */

@RestController
@RequestMapping("/account/main")
@Api(value = "com.boniu.account.api.AccountMainApi", description = "主账户相关接口")
public class AccountMainController implements AccountMainApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountMainService accountMainService;

    /**
     * 获取主账户相关信息
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "获取主账户相关信息", notes = "com.boniu.account.api.AccountMainApi.getInfo")
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public BaseResponse<AccountMainVO> getDetail(@RequestBody QueryAccountMainDetailRequest request) {
        logger.info("#1[获取主账户相关信息]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getMobile())&&StringUtil.isBlank(request.getAccountId())

        ) {
            logger.error("#1[获取主账户相关信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountMainVO> response = new BaseResponse<>();
            AccountMainVO result = accountMainService.getAccountMainDetail(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取主账户相关信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取主账户相关信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_MAIN_ACCOUNT_INFO_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 更新主账户信息
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "更新主账户信息", notes = "com.boniu.account.api.AccountMainApi.update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody UpdateAccountMainRequest request) {
        logger.info("#1[更新主账户信息]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountMainId())) {
            logger.error("#1[更新主账户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountMainService.updateAccountMain(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[更新主账户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新主账户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.UPDATE_MAIN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
