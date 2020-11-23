package com.boniu.account.server.controller;

import com.boniu.account.api.AccountApi;
import com.boniu.account.api.enums.AccountTypeEnum;
import com.boniu.account.api.request.*;
import com.boniu.account.api.vo.AccountCancelVO;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountVO;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.common.ParamValidator;
import com.boniu.account.server.hepler.AccountVipHelper;
import com.boniu.account.server.service.AccountService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.Pagination;
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
import java.util.List;
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

    @Resource
    private AccountVipHelper accountVipHelper;

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
                || (request.getAccountType().equals(AccountTypeEnum.NORMAL.getCode()) && StringUtil.isBlank(request.getMobile()))
                || (request.getAccountType().equals(AccountTypeEnum.NORMAL.getCode()) && StringUtil.isBlank(request.getAccountId()))
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
            logger.error("#1[重设密码]-[密码格式不正确]-request={}", request);
            return new BaseException(AccountErrorEnum.PASSWORD_INCORRECT_FORMAT.getErrorCode()).buildBaseResponse();
        }

        //两次密码确认校验
        if (!StringUtil.equals(request.getFirstPassword(), request.getSecondPassword())) {
            logger.error("#1[重设密码]-[两次输入密码不一致]-request={}", request);
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

    /**
     * 通过手机号查询账户是否存在
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "通过手机号查询账户是否存在", notes = "com.boniu.account.api.AccountApi.queryAccountByMobile")
    @RequestMapping(value = "/queryAccountByMobile", method = RequestMethod.POST)
    public BaseResponse<AccountVO> queryAccountByMobile(@RequestBody QueryAccountByMobileRequest request) {
        logger.info("#1[通过手机号查询账户是否存在]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getMobile())) {
            logger.error("#1[通过手机号查询账户是否存在]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountVO> response = new BaseResponse<>();
            AccountVO result = accountService.queryByMobile(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[通过手机号查询账户是否存在]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[通过手机号查询账户是否存在]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.ACCOUNT_NOT_EXIST.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 保存账户
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "保存账户", notes = "com.boniu.account.api.AccountApi.saveAccount")
    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public BaseResponse<Boolean> saveAccount(@RequestBody SaveAccountRequest request) {
        logger.info("#1[保存账户]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getAccountId())) {
            logger.error("#1[保存账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.saveAccount(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[保存账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[保存账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.SAVE_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 根据邀请码查询账户信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "根据邀请码查询账户信息", notes = "com.boniu.account.api.AccountApi.queryAccountByInviteCode")
    @RequestMapping(value = "/queryAccountByInviteCode", method = RequestMethod.POST)
    public BaseResponse<AccountDetailVO> queryAccountByInviteCode(@RequestBody QueryAccountByInviteCodeRequest request) {
        logger.info("#1[根据邀请码查询账户信息]-[开始]-request={}", request);

        //参数校验
        if (null == request || StringUtil.isBlank(request.getInviteCode())) {
            logger.error("#1[根据邀请码查询账户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountDetailVO> response = new BaseResponse<>();
            AccountDetailVO result = accountService.queryAccountByInviteCode(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[根据邀请码查询账户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[根据邀请码查询账户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_INFO_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 根据参数分页查询账户信息列表
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "根据参数分页查询账户信息列表", notes = "com.boniu.account.api.AccountApi.queryAccountList")
    @RequestMapping(value = "/queryAccountList", method = RequestMethod.POST)
    public BaseResponse<Pagination<List<AccountDetailVO>>> queryAccountList(@RequestBody QueryAccountListRequest request) {
        logger.info("#1[根据参数分页查询账户信息列表]-[开始]-request={}", request);

        //参数校验
        if (null == request ) {
            logger.error("#1[根据参数分页查询账户信息列表]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Pagination<List<AccountDetailVO>>> response = new BaseResponse<>();
            Pagination<List<AccountDetailVO>> result = accountService.queryAccountList(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[根据参数分页查询账户信息列表]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[根据参数分页查询账户信息列表]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_INFO_LIST_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 根据参数分页查询账户信息列表(管理后台)
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "根据参数分页查询账户信息列表", notes = "com.boniu.account.api.AccountApi.queryAccountList")
    @RequestMapping(value = "/queryAccountListForAdmin", method = RequestMethod.POST)
    public BaseResponse<Pagination<List<AccountDetailVO>>> queryAccountListForAdmin(@RequestBody QueryAccountListForAdminRequest request) {
        logger.info("#1[根据参数分页查询账户信息列表]-[开始]-request={}", request);

        //参数校验
        if (null == request ) {
            logger.error("#1[根据参数分页查询账户信息列表]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Pagination<List<AccountDetailVO>>> response = new BaseResponse<>();
            Pagination<List<AccountDetailVO>> result = accountService.queryAccountListForAdmin(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[根据参数分页查询账户信息列表]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[根据参数分页查询账户信息列表]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_INFO_LIST_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 根据参数查询用户信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "根据参数查询用户信息", notes = "com.boniu.account.api.AccountApi.queryAccountList")
    @RequestMapping(value = "/queryAccountListBy", method = RequestMethod.POST)
    public BaseResponse<List<AccountDetailVO>> queryAccountListBy(@RequestBody QueryAccountListByRequest request) {
        logger.info("#1[根据参数查询用户信息]-[开始]-request={}", request);

        //参数校验
        if (null == request ) {
            logger.error("#1[根据参数查询用户信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<List<AccountDetailVO>> response = new BaseResponse<>();
            List<AccountDetailVO> result = accountService.queryAccountListBy(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[根据参数查询用户信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[根据参数查询用户信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_INFO_LIST_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }



    /**
     * 注册并登录账户（新）
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "注册并登录账户", notes = "com.boniu.account.api.AccountApi.registerLoginAccount")
    @RequestMapping(value = "/registerLoginAccount", method = RequestMethod.POST)
    public BaseResponse<AccountVO> registerLoginAccount(@RequestBody RegisterLoginAccountRequest request) {
        logger.info("#1[注册并登录账户]-[开始]-request={}", request);

        BaseResponse<AccountVO> response;

        if (null == request
                || StringUtil.isBlank(request.getMobile())
                || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUuid())
                || StringUtil.isBlank(request.getIp())) {
            logger.error("#1[注册并登录账户]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            AccountVO vo = accountService.registerLoginAccount(request);
            response = new BaseResponse<>(vo);
            response.setSuccess(true);
            logger.info("#1[注册并登录账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[注册并登录账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.LOGIN_ACCOUNT_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 过期VIP账户为普通账户
     *
     * @return
     */
    @Override
    @ApiOperation(value = "过期VIP账户为普通账户", notes = "com.boniu.account.api.AccountApi.vipAccountExpire")
    @RequestMapping(value = "/vipAccountExpire", method = RequestMethod.POST)
    public BaseResponse<Boolean> vipAccountExpire() {
        logger.info("#1[过期VIP账户为普通账户]-[开始]");

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            Boolean result = accountService.vipAccountExpire();
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[过期VIP账户为普通账户]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[过期VIP账户为普通账户]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.VIP_EXPIRE_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 清理注销申请时间为空
     *
     * @return
     */
    @Override
    @ApiOperation(value = "清理注销申请时间为空", notes = "com.boniu.account.api.AccountApi.clearCancelTime")
    @RequestMapping(value = "/clearCancelTime", method = RequestMethod.POST)
    public BaseResponse<Boolean> clearCancelTime() {
        logger.info("#1[清理注销申请时间为空]-[开始]");

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountService.clearCancelTime();
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[清理注销申请时间为空]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[清理注销申请时间为空]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.CLEAR_CANCEL_TIME_FALURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 会员权益消耗
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "会员权益消耗")
    @RequestMapping(value = "/consumeTimesOrLength", method = RequestMethod.POST)
    public BaseResponse<Boolean> consumeTimesOrLength(@RequestBody VipConsumeRequest request) {
        logger.info("#1[会员权益消耗]-[开始]");

        if (null == request
                || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUuid())) {
            logger.error("#1[会员权益消耗]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountVipHelper.consumeTimesOrLength(request.getAccountId(), request.getUuid(), request.getAppName(), request.getTimeLength(), request.getTimes());
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[会员权益消耗]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[会员权益消耗]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.DEFAULT.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 更新用户会员信息
     *
     * @param request
     * @return
     */
    @Override
    @ApiOperation(value = "更新用户会员信息")
    @RequestMapping(value = "/updateAccountVipInfo", method = RequestMethod.POST)
    public BaseResponse<Boolean> updateAccountVipInfo(@RequestBody UpdateAccountVipInfoRequest request) {
        logger.info("#1[更新用户会员信息]-[开始]");

        if (null == request
                || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getUuid())
                || StringUtil.isBlank(request.getOrderId())) {
            logger.error("#1[更新用户会员信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountVipHelper.updateAccountVipForPaySuccess(request.getOrderId(), request.getAppName());
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[更新用户会员信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新用户会员信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.DEFAULT.getErrorCode()).buildBaseResponse();
        }
    }
}
