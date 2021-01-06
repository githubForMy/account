package com.boniu.account.server.controller;

import com.boniu.account.api.AccountCouponApi;
import com.boniu.account.api.enums.AccountCouponStatusEnum;
import com.boniu.account.api.request.AddAccountCouponRequest;
import com.boniu.account.api.request.GetAccountCouponRequest;
import com.boniu.account.api.request.UpdateAccountCouponRequest;
import com.boniu.account.api.vo.AccountCouponVo;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountCouponService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.StringUtil;
import com.boniu.marketing.api.enums.MarketingCouponTypeEnum;
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
 * @Description
 * @Author hanxin
 * @Date 2021/1/4
 */
@RestController
@RequestMapping("/account/coupon")
@Api(tags = "用户优惠券相关接口")
public class AccountCouponController implements AccountCouponApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountCouponService accountCouponService;

    /**
     * 获取用户优惠券信息
     * @param request
     * @return
     */
    @Override
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户优惠券信息")
    public BaseResponse<AccountCouponVo> getDetail(@RequestBody GetAccountCouponRequest request) {
        logger.info("#1[获取用户优惠券信息]-[开始]-request={}", request);
        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountId())
                || StringUtil.isBlank(request.getCouponId())
                || StringUtil.isBlank(request.getAppName())
                || StringUtil.isBlank(request.getType())
                || null == MarketingCouponTypeEnum.getByCode(request.getType())) {
            logger.error("#1[获取用户优惠券信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<AccountCouponVo> response = new BaseResponse<>();
            AccountCouponVo result = accountCouponService.getAccountCouponDetail(request);
            response.setResult(result);
            response.setSuccess(true);
            logger.info("#1[获取用户优惠券信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[获取用户优惠券信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.GET_ACCOUNT_COUPON_DETAIL_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 新增优惠券信息
     * @param request
     * @return
     */
    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增优惠券信息")
    public BaseResponse<Boolean> add(@RequestBody AddAccountCouponRequest request) {
        logger.info("#1[新增优惠券信息]-[开始]-request={}", request);
        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountId())
                || StringUtil.isBlank(request.getCouponId())
                || StringUtil.isBlank(request.getSource())
                || StringUtil.isBlank(request.getType())
                || null == MarketingCouponTypeEnum.getByCode(request.getType())) {
            logger.error("#1[新增优惠券信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountCouponService.addAccountCoupon(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[新增优惠券信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[新增优惠券信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.ADD_ACCOUNT_COUPON_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }

    /**
     * 更新优惠券信息
     * @param request
     * @return
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新优惠券信息")
    public BaseResponse<Boolean> update(@RequestBody UpdateAccountCouponRequest request) {
        logger.info("#1[更新优惠券信息]-[开始]-request={}", request);
        //参数校验
        if (null == request
                || StringUtil.isBlank(request.getAccountCouponId())
                || StringUtil.isBlank(request.getStatus())
                || null == AccountCouponStatusEnum.getByCode(request.getStatus())) {
            logger.error("#1[更新优惠券信息]-[参数异常]-request={}", request);
            return new BaseException(AccountErrorEnum.INVALID_PARAM.getErrorCode()).buildBaseResponse();
        }

        try {
            BaseResponse<Boolean> response = new BaseResponse<>();
            accountCouponService.updateAccountCoupon(request);
            response.setResult(true);
            response.setSuccess(true);
            logger.info("#1[更新优惠券信息]-[成功]-response={}", response);
            return response;
        } catch (Exception e) {
            logger.error("#1[更新优惠券信息]-[失败]", e);
            return new BaseException(e, AccountErrorEnum.UPDATE_ACCOUNT_COUPON_FAILURE.getErrorCode()).buildBaseResponse();
        }
    }
}
