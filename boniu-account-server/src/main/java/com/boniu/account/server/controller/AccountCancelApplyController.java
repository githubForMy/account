package com.boniu.account.server.controller;

import com.boniu.account.api.AccountCancelApplyApi;
import com.boniu.account.api.vo.AccountCancelApplyVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountCancelApplyService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
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
 * @Description 账户注销
 * @Author hanxin
 * @Date 2020/11/5
 */

@RestController
@RequestMapping("/account/cancel")
@Api(tags = "账户注销相关接口")
public class AccountCancelApplyController implements AccountCancelApplyApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountCancelApplyService accountCancelApplyService;

    /**
     * 获取注销信息
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "获取注销信息")
    @RequestMapping(value = "/getApplyInfo", method = RequestMethod.POST)
    public BaseResponse<AccountCancelApplyVO> getApplyInfo(@RequestBody BaseRequest request) {
        logger.info("#1[获取注销信息]-[开始]-request={}", request);

        if(null == request || StringUtil.isBlank(request.getAppName()) || StringUtil.isBlank(request.getUuid()) || StringUtil.isBlank(request.getAccountId())) {
            logger.error("#1[获取注销信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountCancelApplyVO> response = new BaseResponse<>();
            AccountCancelApplyVO result = accountCancelApplyService.getApplyInfo(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取注销信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取注销信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_CANCEL_APPLY_INFO_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 申请注销
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "申请注销")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public BaseResponse<Boolean> apply(BaseRequest request) {
        logger.info("#1[申请注销]-[开始]-request={}", request);

        if(null == request || StringUtil.isBlank(request.getAppName()) || StringUtil.isBlank(request.getUuid()) || StringUtil.isBlank(request.getAccountId())) {
            logger.error("#1[申请注销]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountCancelApplyService.apply(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[申请注销]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[申请注销]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.APPLY_ACCOUNT_CANCEL_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 取消申请注销
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "取消申请注销")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public BaseResponse<Boolean> cancel(BaseRequest request) {
        logger.info("#1[取消申请注销]-[开始]-request={}", request);

        if(null == request || StringUtil.isBlank(request.getAppName()) || StringUtil.isBlank(request.getUuid()) || StringUtil.isBlank(request.getAccountId())) {
            logger.error("#1[取消申请注销]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountCancelApplyService.cancel(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[取消申请注销]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[取消申请注销]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.CANCEL_ACCOUNT_CANCEL_APPLY_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
