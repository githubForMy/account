package com.boniu.account.server.controller;

import com.boniu.account.api.AccountApi;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.common.ParamValidator;
import com.boniu.account.server.service.AccountService;
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
 * @ClassName AccountController
 * @Description 账户相关接口
 * @Author HanXin
 * @Date 2019-06-24
 */
@RestController
@RequestMapping("/account")
@Api(value = "com.boniu.account.api.AccountApi", description = "账户相关接口")
public class AccountController implements AccountApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountService accountService;


    /**
     * 验证账户是否已注册
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "验证账户是否已注册", notes = "com.boniu.account.api.AccountApi.checkAccount")
    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    public BaseResponse<Boolean> checkAccount(@RequestBody CheckAccountRequest request) {
        logger.info("#1[验证手机号码是否已注册]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[验证手机号码是否已注册]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.checkAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[验证手机号码是否已注册]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[验证手机号码是否已注册]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.CHECK_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 注册账户
     * TODO:海外版本注册暂无，需求有的时候再补充
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "注册账户", notes = "com.boniu.account.api.AccountApi.registerAccount")
    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
    public BaseResponse<Boolean> registerAccount(@RequestBody RegisterAccountRequest request) {
        logger.info("#1[注册账户]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[注册账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.registerAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[注册账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[注册账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.REGISTER_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 登录账户
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "账户登录", notes = "com.boniu.account.api.AccountApi.loginAccount")
    @RequestMapping(value = "/loginAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> loginAccount(@RequestBody LoginAccountRequest request) {
        logger.info("#1[账户登录]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[账户登录]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountVO> response = new BaseResponse<>();
            AccountVO vo = accountService.loginAccount(request);
            response.setResult(vo);
            response.setSuccess(true);
            logger.info("#1[账户登录]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[账户登录]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "注销登录", notes = "com.boniu.account.api.AccountApi.logoutAccount")
    @RequestMapping(value = "/logoutAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> logoutAccount(@RequestBody BaseAccountRequest request) {
        logger.info("#1[注销登录]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[注销登录]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountVO> response = new BaseResponse<>();
            AccountVO result = accountService.logoutAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[注销登录]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[注销登录]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.LOGOUT_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 获取账户详细信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "获取账户详细信息", notes = "com.boniu.account.api.AccountApi.getAccountInfo")
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST)
    public BaseResponse<AccountDetailVO> getAccountInfo(@RequestBody BaseAccountRequest request) {
        logger.info("#1[获取账户详细信息]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[获取账户详细信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountDetailVO> response = new BaseResponse<>();
            AccountDetailVO result = accountService.getAccountInfo(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取账户详细信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取账户详细信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_INFO_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 通过token获取新的加密accountId
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "token获取新登录信息", notes = "com.boniu.account.api.AccountApi.getNewAccountId")
    @RequestMapping(value = "/getNewAccountId", method = RequestMethod.POST)
    public BaseResponse<String> getNewAccountId(@RequestBody TokenAccountRequest request) {
        logger.info("#1[token获取新登录信息]-[开始]-request={}", request);

        BaseResponse<String> response;
        if(null==request
            || StringUtil.isBlank(request.getToken())
             || request.getToken().length() != 32){
            logger.error("#1[token获取新登录信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            String result = accountService.getNewAccountId(request);

            response = new BaseResponse(result);
            response.setSuccess(true);
            logger.info("#1[token获取新登录信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[token获取新登录信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_NEW_ACCOUNT_ID_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 更新账户信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "更新账户信息", notes = "com.boniu.account.api.AccountApi.updateAccountInfo")
    @RequestMapping(value = "/updateAccountInfo", method = RequestMethod.POST)
    public BaseResponse<Boolean> updateAccountInfo(@RequestBody UpdateAccountRequest request) {
        logger.info("#1[更新账户信息]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[更新账户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.updateAccountInfo(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[更新账户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新账户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.UPDATE_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
