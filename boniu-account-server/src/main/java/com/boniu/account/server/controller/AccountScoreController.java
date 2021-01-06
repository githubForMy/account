package com.boniu.account.server.controller;

import com.boniu.account.api.AccountScoreApi;
import com.boniu.account.api.request.AddAccountScoreRequest;
import com.boniu.account.api.request.UpdateAccountScoreRequest;
import com.boniu.account.api.result.AccountScoreResult;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountScoreService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.StringUtil;
import com.boniu.common.help.AppNameHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: FengXian
 * @create: 2021-01-06 11:06
 **/
@RestController
@RequestMapping("/account/score")
@Api(tags = "用户积分信息相关接口")
public class AccountScoreController implements AccountScoreApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountScoreService accountScoreService;
    @Autowired
    private AppNameHelper appNameHelper;

    @Override
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<Boolean> add(@RequestBody AddAccountScoreRequest request) {
        logger.info("#1[新增用户信息]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountId())
                || StringUtil.isBlank(request.getAppName())
                || appNameHelper.checkAppName(request.getAppName())
        ) {
            logger.error("#1[新增用户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountScoreService.addAccountScore(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[新增用户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[新增用户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.ADD_ACCOUNT_SCORE_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    @Override
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody UpdateAccountScoreRequest request) {
        logger.info("#1[更新用户信息]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountId())
        ) {
            logger.error("#1[更新用户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountScoreService.updateAccountScore(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[更新用户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新用户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.UPDATE_ACCOUNT_SCORE_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    @Override
    @ApiOperation(value = "获取用户积分信息", notes = "获取用户积分信息")
    @RequestMapping(value = "/getAccountScore", method = RequestMethod.POST)
    public BaseResponse<AccountScoreResult> getAccountScore(BaseRequest request) {
        logger.info("#1[获取用户积分信息]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountId())
        ) {
            logger.error("#1[获取用户积分信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountScoreResult> response = new BaseResponse<>();
            AccountScoreResult result = accountScoreService.getAccountScore(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取用户积分信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取用户积分信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_SCORE_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
