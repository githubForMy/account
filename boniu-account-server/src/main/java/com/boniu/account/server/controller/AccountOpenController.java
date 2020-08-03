package com.boniu.account.server.controller;

import com.boniu.account.api.AccountOpenApi;
import com.boniu.account.api.request.OpenAccountGetRequest;
import com.boniu.account.api.request.OpenAuthorizedLoginRequest;
import com.boniu.account.api.request.OpenGetAccessTokenRequest;
import com.boniu.account.api.request.OpenUpdateAccountRequest;
import com.boniu.account.api.vo.OpenAccessTokenVO;
import com.boniu.account.api.vo.OpenAccountVO;
import com.boniu.account.api.vo.OpenAuthorizedLoginVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountOpenService;
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
 * @ClassName AccountOpenOauthController
 * @Description 账户开放平台相关接口
 * @Author HanXin
 * @Date 2020-06-11
 */
@RestController
@RequestMapping("/account/open")
@Api(value = "com.boniu.account.api.AccountOpenApi", description = "账户开放平台相关接口")
public class AccountOpenController implements AccountOpenApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountOpenService accountOpenService;

    @Override
    @ApiOperation(value = "获取授权凭证", notes = "通过appId和masterSecret获取accessToken，appId和masterSecret由平台方面提供")
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    public BaseResponse<OpenAccessTokenVO> getAccessToken(@RequestBody OpenGetAccessTokenRequest request) {
        logger.info("#1[获取授权凭证]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAppId())
                || StringUtil.isBlank(request.getMasterSecret())) {
            logger.error("#1[获取授权凭证]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<OpenAccessTokenVO> response = new BaseResponse<>();
            OpenAccessTokenVO result = accountOpenService.getAccessToken(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取授权凭证]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取授权凭证]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCESS_TOKEN_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    @Override
    @ApiOperation(value = "授权登录", notes = "账户授权登录")
    @RequestMapping(value = "/authorized/login", method = RequestMethod.POST)
    public BaseResponse<OpenAuthorizedLoginVO> authorizedLogin(@RequestBody OpenAuthorizedLoginRequest request) {
        logger.info("#1[授权登录]-[开始]-request={}", request);

        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccessToken())
                || StringUtil.isBlank(request.getMobile())) {
            logger.error("#1[授权登录]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<OpenAuthorizedLoginVO> response = new BaseResponse<>();
            OpenAuthorizedLoginVO result = accountOpenService.authorizedLogin(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[授权登录]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[授权登录]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.AUTHORIZED_LOGIN_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    @Override
    @ApiOperation(value = "更新开放平台账户数据", notes = "更新开放平台账户数据")
    @RequestMapping(value = "/updateOpenAccount", method = RequestMethod.POST)
    public BaseResponse<Boolean> updateOpenAccount(@RequestBody OpenUpdateAccountRequest request) {
        logger.info("#1[更新开放平台账户数据]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getOpenId())) {
            logger.error("#1[更新开放平台账户数据]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountOpenService.updateOpenAccount(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[更新开放平台账户数据]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新开放平台账户数据]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.UPDATE_OPEN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    @Override
    @ApiOperation(value = "获取开放平台账户数据", notes = "获取开放平台账户数据")
    @RequestMapping(value = "/getOpenAccountInfo", method = RequestMethod.POST)
    public BaseResponse<OpenAccountVO> getOpenAccountInfo(@RequestBody OpenAccountGetRequest request) {
        logger.info("#1[获取开放平台账户数据]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getOpenId())) {
            logger.error("#1[获取开放平台账户数据]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<OpenAccountVO> response = new BaseResponse<>();
            OpenAccountVO result = accountOpenService.getOpenAccountInfo(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取开放平台账户数据]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取开放平台账户数据]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_OPEN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }


}
