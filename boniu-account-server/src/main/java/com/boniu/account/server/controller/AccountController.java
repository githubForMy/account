package com.boniu.account.server.controller;

import com.boniu.account.api.AccountApi;
import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountCancelVO;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.common.ParamValidator;
import com.boniu.account.server.service.AccountService;
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
import java.util.regex.Pattern;

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
     * 登录账户
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "账户登录", notes = "com.boniu.account.api.AccountApi.loginAccount")
    @RequestMapping(value = "/loginAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> loginAccount(@RequestBody LoginAccountRequest request) {
        logger.info("#1[账户登录]-[开始]-request={}", request);

        BaseResponse<AccountVO> response;
        if (!ParamValidator.validate(request)) {
            logger.error("#1[账户登录]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        if(null==request
                || StringUtil.isBlank(request.getAccountType())
                || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUuid())
                || StringUtil.isBlank(request.getIp())
                || (request.getAccountType().equals(AccountTypeEnum.NORMAL.getCode())&&StringUtil.isBlank(request.getMobile()))
                || (request.getAccountType().equals(AccountTypeEnum.VISITOR.getCode()) && StringUtil.isNotBlank(request.getMobile()))){
            logger.error("#1[账户登录]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            AccountVO vo = accountService.loginAccount(request);
            response = new BaseResponse<>(vo);
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
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "注销登录", notes = "com.boniu.account.api.AccountApi.logoutAccount")
    @RequestMapping(value = "/logoutAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> logoutAccount(@RequestBody BaseRequest request) {
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
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "获取账户详细信息", notes = "com.boniu.account.api.AccountApi.getAccountInfo")
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST)
    public BaseResponse<AccountDetailVO> getAccountInfo(@RequestBody BaseRequest request) {
        logger.info("#1[获取账户详细信息]-[开始]-request={}", request);

        BaseResponse<AccountDetailVO> response;
        if (!ParamValidator.validate(request)) {
            logger.error("#1[获取账户详细信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            AccountDetailVO result = accountService.getAccountInfo(request);
            response = new BaseResponse<>(result);
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

    /**
     * 验证用户名是否已注册
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "验证用户名是否已注册", notes = "com.boniu.account.api.AccountApi.checkUserName")
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    public BaseResponse<Boolean> checkUserName(@RequestBody CheckUserNameRequest request) {
        logger.info("#1[验证用户名是否已注册]-[开始]-request={}", request);

        //参数校验
        if (!ParamValidator.validate(request)) {
            logger.error("#1[验证用户名是否已注册]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.checkUserName(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[验证用户名是否已注册]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[验证用户名是否已注册]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.CHECK_USERNAME_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 注册账户(海外版本)
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
        if (null == request
                || StringUtil.isBlank(request.getUserName())
                || StringUtil.isBlank(request.getIp())
                || StringUtil.isBlank(request.getFirstPassword())
                || StringUtil.isBlank(request.getSecondPassword())) {
            logger.error("#1[注册账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        //密码格式校验
        if (request.getFirstPassword().length() < 8 || request.getFirstPassword().length() > 16) {
            logger.error("#1[注册账户]-[密码格式不正确]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_INCORRECT_FORMAT.getErrorCode()).buildBaseResponse();
        }

        //两次密码确认校验
        if (!StringUtil.equals(request.getFirstPassword(), request.getSecondPassword())) {
            logger.error("#1[注册账户]-[两次输入密码不一致]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_NOT_MATCH.getErrorCode()).buildBaseResponse();
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
     * 登录账户（海外版本）
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "登录账户(用户名密码登录)", notes = "com.boniu.account.api.AccountApi.loginOverseasAccount")
    @RequestMapping(value = "/loginOverseasAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> loginOverseasAccount(@RequestBody LoginOverseasAccountRequest request) {
        logger.info("#1[登录账户]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUserName())
                || StringUtil.isBlank(request.getPassword())
                || StringUtil.isBlank(request.getIp())) {
            logger.error("#1[登录账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountVO> response = new BaseResponse<>();
            AccountVO result = accountService.loginOverseasAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[登录账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[登录账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 修改账户登录密码
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "修改账户登录密码", notes = "com.boniu.account.api.AccountApi.modifyLoginPassword")
    @RequestMapping(value = "/modifyLoginPassword", method = RequestMethod.POST)
    public BaseResponse<Boolean> modifyLoginPassword(@RequestBody UpdateLoginPasswordRequest request) {
        logger.info("#1[修改账户登录密码]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getAccountId())
                || StringUtil.isBlank(request.getFirstNewPassword())
                || StringUtil.isBlank(request.getSecondNewPassword())
                || StringUtil.isBlank(request.getUsername())) {
            logger.error("#1[修改账户登录密码]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        //密码格式校验
        if (request.getFirstNewPassword().length() < 8 || request.getFirstNewPassword().length() > 16) {
            logger.error("#1[修改账户登录密码]-[密码格式不正确]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_INCORRECT_FORMAT.getErrorCode()).buildBaseResponse();
        }

        //两次密码确认校验
        if (!StringUtil.equals(request.getFirstNewPassword(), request.getSecondNewPassword())) {
            logger.error("#1[修改账户登录密码]-[两次输入密码不一致]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_NOT_MATCH.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.modifyLoginPassword(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[修改账户登录密码]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[修改账户登录密码]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.MODIFY_PASSWORD_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 绑定邮箱地址到账户
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "绑定邮箱地址到账户", notes = "com.boniu.account.api.AccountApi.bindEmail")
    @RequestMapping(value = "/bindEmail", method = RequestMethod.POST)
    public BaseResponse<Boolean> bindEmail(@RequestBody BindEmailRequest request) {
        logger.info("#1[绑定邮箱地址到账户]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getAccountId())
                || StringUtil.isBlank(request.getEmail())) {
            logger.error("#1[绑定邮箱地址到账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        //邮箱格式校验
        String pattern = "^[A-Za-z0-9]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!Pattern.matches(pattern, request.getEmail())) {
            logger.error("#1[绑定邮箱地址到账户]-[邮箱地址格式不正确]-request={}", request);
            return new BaseException(AccountErrorEnum.EMAIL_INCORRECT_FORMAT.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.bindEmail(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[绑定邮箱地址到账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[绑定邮箱地址到账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.BIND_EMIAL_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 忘记密码找回-重设密码
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "重设密码", notes = "com.boniu.account.api.AccountApi.resetPassword")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public BaseResponse<Boolean> resetPassword(@RequestBody ResetPasswordRequest request) {
        logger.info("#1[重设密码]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUserName())
                || StringUtil.isBlank(request.getFirstPassword())
                || StringUtil.isBlank(request.getSecondPassword())) {
            logger.error("#1[重设密码]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        //密码格式校验
        if (request.getFirstPassword().length() < 8 || request.getFirstPassword().length() > 16) {
            logger.error("#1[注册账户]-[密码格式不正确]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_INCORRECT_FORMAT.getErrorCode()).buildBaseResponse();
        }

        //两次密码确认校验
        if (!StringUtil.equals(request.getFirstPassword(), request.getSecondPassword())) {
            logger.error("#1[注册账户]-[两次输入密码不一致]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_NOT_MATCH.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.resetPassword(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[重设密码]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[重设密码]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.RESET_PASSWORD_FAIL.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 账户注销
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "账户注销", notes = "com.boniu.account.api.AccountApi.cancelAccount")
    @RequestMapping(value = "/cancelAccount", method = RequestMethod.POST)
    public BaseResponse<AccountCancelVO> cancelAccount(@RequestBody BaseRequest request) {
        logger.info("#1[账户注销]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getAccountId())) {
            logger.error("#1[账户注销]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountCancelVO> response = new BaseResponse<>();
            AccountCancelVO result = accountService.cancelAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[账户注销]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[账户注销]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.CANCEL_ACCOUNT_FAIL.getErrorCode()).buildBaseResponse();
        }
    }
}
