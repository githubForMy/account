package com.boniu.account.server.hepler;

import com.boniu.account.api.enums.AccountVipInfoStatusEnum;
import com.boniu.account.api.enums.AccountVipInfoTypeEnum;
import com.boniu.account.repository.api.AccountMapper;
import com.boniu.account.repository.api.AccountVipInfoMapper;
import com.boniu.account.repository.entity.AccountEntity;
import com.boniu.account.repository.entity.AccountVipInfoEntity;
import com.boniu.account.server.client.PayClient;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.pojo.AccountVipInfoPoJo;
import com.boniu.base.utile.enums.BooleanEnum;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseResponse;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import com.boniu.common.help.AppNameHelper;
import com.boniu.pay.api.request.QueryOrderDetailRequest;
import com.boniu.pay.api.vo.OrderDetailVO;
import com.boniu.pay.api.vo.PayProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ZZF on 2020/6/13.
 */
@Component
public class AccountVipHelper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountVipInfoMapper vipInfoMapper;
    @Resource
    private AppNameHelper appNameHelper;
    @Resource
    private PayClient payClient;
    @Resource
    private AccountMapper accountMapper;


    /**
     * 额外条件判断app能否购买
     *
     * @param appName
     * @param accountId
     * @param uuid
     */
    public void limitAccountIsCanBug(String appName, String accountId, String uuid) {
        AccountVipInfoPoJo accountVipInfo = this.getNowVipInfo(accountId, uuid, appName);
        //如果不是普通用户
        if (null != accountVipInfo && !accountVipInfo.getVipType().equals(AccountVipInfoTypeEnum.NORMAL.getCode())) {
            if (appNameHelper.checkAppName(appName)) {
                logger.error("#1[额外条件判断app能否购买]-[您当前已是会员，无需重复购买]-appName={},accountId={},uuid={}", appName, accountId, uuid);
                throw new BaseException(AccountErrorEnum.ALREADY_VIP_ACCOUNT.getErrorCode());
            }
        }
    }

    /**
     * 支付成功后，更新用户会员权益
     *
     * @param orderId
     * @param appName
     */
    public void updateAccountVipForPaySuccess(String orderId, String appName) {
        logger.error("#1[更新会员权益]-[开始更新会员权益开始]-orderId={}", orderId);

        //获取订单信息
        QueryOrderDetailRequest queryOrderDetailRequest = new QueryOrderDetailRequest();
        queryOrderDetailRequest.setOrderId(orderId);
        BaseResponse<OrderDetailVO> orderDetailResponse = payClient.queryDetail(queryOrderDetailRequest);
        if (null == orderDetailResponse || !orderDetailResponse.isSuccess() || null == orderDetailResponse.getResult()) {
            logger.error("#1[更新会员权益]-[获取订单信息失败]-request={}", orderDetailResponse);
            throw new BaseException(orderDetailResponse);
        }

        OrderDetailVO orderDetailVO = orderDetailResponse.getResult();

        //查询产品
        PayProductVo payProductVo = payClient.getInfo(orderDetailVO.getProductId()).getResult();
        String vipType = "";
        Boolean isExpireTime = null;
        Boolean isLimitTimes = null;
        Boolean isLimitTimeLength = null;
        Boolean forever = null;

        String payProductType = payProductVo.getType();       //产品类型
        if (payProductType.contains("S_")) {
            if (payProductType.contains("DAY")) {
                vipType = AccountVipInfoTypeEnum.SVIP.getCode();
                isExpireTime = true;
            } else if (payProductType.contains("FOREVER")) {
                vipType = AccountVipInfoTypeEnum.SVIP.getCode();
                forever = true;
                isExpireTime = true;
            } else if (payProductType.contains("TIMES")) {
                vipType = AccountVipInfoTypeEnum.SVIP.getCode();
                isLimitTimes = true;
            } else if (payProductType.contains("TIME_LENGTH")) {
                vipType = AccountVipInfoTypeEnum.SVIP.getCode();
                isLimitTimeLength = true;
            }
        } else {
            if (payProductType.contains("DAY")) {
                vipType = AccountVipInfoTypeEnum.VIP.getCode();
                isExpireTime = true;
            } else if (payProductType.contains("FOREVER")) {
                vipType = AccountVipInfoTypeEnum.VIP.getCode();
                forever = true;
                isExpireTime = true;
            } else if (payProductType.contains("TIMES")) {
                vipType = AccountVipInfoTypeEnum.VIP.getCode();
                isLimitTimes = true;
            } else if (payProductType.contains("TIME_LENGTH")) {
                vipType = AccountVipInfoTypeEnum.VIP.getCode();
                isLimitTimeLength = true;
            }
        }
        if (StringUtil.isBlank(vipType)) {
            logger.error("#1[更新会员权益]-[处理会员类型失败,productType配置有误]-orderDetailVO={}", orderDetailVO);
            return;
        }

        //查询原来是否存在这类会员
        AccountVipInfoEntity vipInfoQuery = new AccountVipInfoEntity();
        if (StringUtil.isNotBlank(orderDetailVO.getAccountId())) {
            vipInfoQuery.setAccountId(orderDetailVO.getAccountId());
        } else {
            vipInfoQuery.setAccountIdNull(BooleanEnum.YES.getCode());
            vipInfoQuery.setAppName(orderDetailVO.getAppName());
            vipInfoQuery.setUuid(orderDetailVO.getUuid());
        }
        vipInfoQuery.setExpireTimeExist(isExpireTime);
        vipInfoQuery.setLimitTimesExist(isLimitTimes);
        vipInfoQuery.setLimitTimeLengthExist(isLimitTimeLength);
        vipInfoQuery.setForeverExist(forever);
        List<AccountVipInfoEntity> vipInfoList = vipInfoMapper.getVipInfoBy(vipInfoQuery);

        Date now = new Date();
        Integer num = payProductVo.getNum();

        //如果不存在，则新增数据
        if (vipInfoList.size() == 0) {
            AccountVipInfoEntity vipInfoSave = new AccountVipInfoEntity();
            vipInfoSave.setAccountVipId(IDUtils.createID());
            vipInfoSave.setAccountId(orderDetailVO.getAccountId());
            vipInfoSave.setVipType(vipType);
            vipInfoSave.setStatus(AccountVipInfoStatusEnum.NORMAL.getCode());
            vipInfoSave.setIsUseing(BooleanEnum.YES.getCode());
            vipInfoSave.setUuid(orderDetailVO.getUuid());
            vipInfoSave.setAppName(appName);
            //时间类型
            if (null != isExpireTime && isExpireTime) {
                if (payProductType.contains("FOREVER")) {
                    vipInfoSave.setIsForever(BooleanEnum.YES.getCode());
                } else {
                    vipInfoSave.setExpireTime(DateUtil.getDiffDay(now, num));
                    //如果是自动订阅，过期时间已苹果返回为准
                    if (StringUtil.isNotBlank(payProductVo.getAutoPay())
                            && payProductVo.getAutoPay().equals(BooleanEnum.YES.getCode())) {
                        vipInfoSave.setExpireTime(orderDetailVO.getExpiresTime());
                    }
                }
                //次数类型
            } else if (null != isLimitTimes && isLimitTimes) {
                vipInfoSave.setLimitTimes(payProductVo.getNum());
                //时长类型
            } else if (null != isLimitTimeLength && isLimitTimeLength) {
                vipInfoSave.setLimitTimeLength(payProductVo.getNum());
            }
            int count = vipInfoMapper.saveVipInfo(vipInfoSave);
            if (count == 0) {
                logger.error("#1[更新会员权益]-[新增会员信息表失败]-orderEntity={},accountVipInfo={}", orderDetailVO, vipInfoSave);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
            //如果已经存在数据，则更新数据时间或者次数等
        } else {
            AccountVipInfoEntity vipInfoTemp = vipInfoList.get(0);
            AccountVipInfoEntity vipInfoUpdate = new AccountVipInfoEntity();
            vipInfoUpdate.setAccountVipId(vipInfoTemp.getAccountVipId());
            vipInfoUpdate.setStatus(AccountVipInfoStatusEnum.NORMAL.getCode());
            vipInfoUpdate.setIsUseing(BooleanEnum.NO.getCode());
            //时间类型
            if (null != isExpireTime && isExpireTime) {
                if (payProductType.contains("FOREVER")) {
                    vipInfoUpdate.setIsForever(BooleanEnum.YES.getCode());
                } else {
                    if (vipInfoTemp.getExpireTime().before(now)) {
                        vipInfoUpdate.setExpireTime(DateUtil.getDiffDay(now, num));
                    } else {
                        vipInfoUpdate.setExpireTime(DateUtil.getDiffDay(vipInfoTemp.getExpireTime(), num));

                    }
                    //如果是自动订阅，过期时间已苹果返回为准
                    if (StringUtil.isNotBlank(payProductVo.getAutoPay())
                            && payProductVo.getAutoPay().equals(BooleanEnum.YES.getCode())) {
                        vipInfoUpdate.setExpireTime(orderDetailVO.getExpiresTime());
                    }
                }
                //次数类型
            } else if (null != isLimitTimes && isLimitTimes) {
                vipInfoUpdate.setLimitTimes(vipInfoTemp.getLimitTimes() + num);
                //时长类型
            } else if (null != isLimitTimeLength && isLimitTimeLength) {
                vipInfoUpdate.setLimitTimeLength(vipInfoTemp.getLimitTimeLength() + num);
            }
            int count = vipInfoMapper.updateVipInfo(vipInfoUpdate);
            if (count == 0) {
                logger.error("#1[更新会员权益]-[更新会员信息表失败]-orderEntity={},accountVipInfo={}", orderDetailVO, vipInfoUpdate);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
            //处理最高等级的会员权益标识
            this.handleAccountVipInfoForAccount(orderDetailVO.getAccountId(), orderDetailVO.getUuid(), appName);
        }
        logger.info("#1[更新会员权益]-[结束]-orderId={},", orderDetailVO.getOrderId());
    }

    /**
     * 获取当前的会员权益新信息
     *
     * @param accountId
     * @param uuid
     * @param appName
     * @return
     */
    public AccountVipInfoPoJo getNowVipInfo(String accountId, String uuid, String appName) {
        AccountVipInfoPoJo result = new AccountVipInfoPoJo();

        AccountVipInfoEntity entityQuery = new AccountVipInfoEntity();
        entityQuery.setAppName(appName);
        entityQuery.setIsUseing(BooleanEnum.YES.getCode());
        if (StringUtil.isNotBlank(accountId)) {
            entityQuery.setAccountId(accountId);
        } else {
            entityQuery.setAccountIdNull(BooleanEnum.YES.getCode());
            entityQuery.setUuid(uuid);
        }

        List<AccountVipInfoEntity> list = vipInfoMapper.getVipInfoBy(entityQuery);
        if (list.size() > 0) {
            AccountVipInfoEntity temp = list.get(0);
            if (StringUtil.equals(temp.getIsForever(), BooleanEnum.YES.getCode())) {
                result.setVipType("FOREVER_" + temp.getVipType());
            } else {
                //按时间的，且已过期 // 按次数的，且已经为0  //按时长的，且已经为0  = 需要重新处理最新的会员记录
                if ((null != temp.getExpireTime() && temp.getExpireTime().before(new Date()))
                        || (null != temp.getLimitTimes() && temp.getLimitTimes() <= 0)
                        || (null != temp.getLimitTimeLength() && temp.getLimitTimeLength() <= 0)) {
                    temp = this.handleAccountVipInfoForAccount(accountId, uuid, appName);
                }
                //如果存在，则一定是可用的
                if (null != temp) {
                    if (StringUtil.equals(temp.getIsForever(), BooleanEnum.YES.getCode())) {
                        result.setVipType("FOREVER_" + temp.getVipType());
                    } else {
                        result.setVipType(temp.getVipType());
                        result.setVipExpireTime(temp.getExpireTime());
                        if (null != temp.getExpireTime()) {
                            double expriseDays = (double) (temp.getExpireTime().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);
                            int days = (int) Math.ceil(expriseDays);
                            result.setVipExpireDays(days);
                        }
                        result.setVipLimitTimes(temp.getLimitTimes());
                        result.setVipLimitTimeLength(temp.getLimitTimeLength());
                    }
                }
            }
        } else {
            result.setVipType(AccountVipInfoTypeEnum.NORMAL.getCode());

            //如果无会员标识则更新自动订阅账户信息的auto_pay为NO

            if (StringUtil.isNotBlank(accountId)) {
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setAccountId(accountId);
                accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                accountEntity.setUpdateTime(new Date());
                accountMapper.updateAccount(accountEntity);
            } else {
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setUuid(uuid);
                accountEntity.setAppName(appName);
                accountEntity.setAutoPay(BooleanEnum.NO.getCode());
                accountEntity.setUpdateTime(new Date());
                accountMapper.updateAccountByUuidAndAppName(accountEntity);

            }


        }
        return result;
    }

    /**
     * 次数或时长消耗
     *
     * @param accountId
     * @param uuid
     * @param appName
     */
    public void consumeTimesOrLength(String accountId, String uuid, String appName, Integer length, Integer times) {
        AccountVipInfoEntity accountVipInfoEntity = this.handleAccountVipInfoForAccount(accountId, uuid, appName);

        if (accountVipInfoEntity.getLimitTimes() <= 0 || accountVipInfoEntity.getLimitTimeLength() <= 0) {
            logger.error("#1[会员权益次数或时长消耗]-[会员权益已消耗完]-accountId={},uuid={},appName={}", accountId, uuid, appName);
            throw new BaseException(AccountErrorEnum.DATA_ERROR.getErrorCode());
        }


        AccountVipInfoEntity vipInfoUpdate = new AccountVipInfoEntity();
        vipInfoUpdate.setAccountVipId(accountVipInfoEntity.getAccountVipId());
        vipInfoUpdate.setLimitTimes(accountVipInfoEntity.getLimitTimes() - times);
        vipInfoUpdate.setLimitTimeLength(accountVipInfoEntity.getLimitTimeLength() - length);
        int count = vipInfoMapper.updateVipInfo(vipInfoUpdate);
        if (count == 0) {
            logger.error("#1[更新会员权益]-[更新会员信息表失败]-accountId={},uuid={},appName={}", accountId, uuid, appName);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }

    /**
     * 处理最高等级的会员权益标识
     *
     * @param accountId 用户标识
     * @param uuid      设备标识（游客通过设备标识处理）
     * @param appName
     */
    private AccountVipInfoEntity handleAccountVipInfoForAccount(String accountId, String uuid, String appName) {
        logger.error("#1[处理最高等级的会员权益标识]-[开始]-accountId={},uuid={},appName={}", accountId, uuid, appName);
        AccountVipInfoEntity vipInfoQuery = new AccountVipInfoEntity();
        vipInfoQuery.setStatus(AccountVipInfoStatusEnum.NORMAL.getCode());
        if (StringUtil.isNotBlank(accountId)) {
            vipInfoQuery.setAccountId(accountId);
        } else {
            vipInfoQuery.setAccountIdNull(BooleanEnum.YES.getCode());
            vipInfoQuery.setUuid(uuid);
            vipInfoQuery.setAppName(appName);
        }
        List<AccountVipInfoEntity> list = vipInfoMapper.getVipInfoBy(vipInfoQuery);

        if (list.size() > 0) {
            //处理SVIP-----------------------
            //寻找    永久SVIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.SVIP.getCode()) && StringUtil.isNotBlank(temp.getIsForever()) && temp.getIsForever().equals(BooleanEnum.YES.getCode())) {
                    this.updateAccountVipUseing(temp.getAccountVipId());
                    logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                    return temp;
                }
            }
            //寻找    时间类型SVIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.SVIP.getCode()) && null != temp.getExpireTime()) {
                    if (temp.getExpireTime().after(new Date())) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                        //如果已经过期-更改状态-结束循环
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }
            //寻找    按时长类型SVIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.SVIP.getCode()) && null != temp.getLimitTimeLength()) {
                    if (temp.getLimitTimeLength() > 0) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }
            //寻找    按次数类型SVIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.SVIP.getCode()) && null != temp.getLimitTimes()) {
                    if (temp.getLimitTimes() > 0) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }

            //处理VIP-----------------------
            //寻找    永久VIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.VIP.getCode()) && StringUtil.isNotBlank(temp.getIsForever()) && temp.getIsForever().equals(BooleanEnum.YES.getCode())) {
                    this.updateAccountVipUseing(temp.getAccountVipId());
                    logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                    return temp;
                }
            }
            //寻找    时间类型VIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.VIP.getCode()) && null != temp.getExpireTime()) {
                    if (temp.getExpireTime().after(new Date())) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.info("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                        //如果已经过期-更改状态-结束循环
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }
            //寻找    按时长类型VIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.VIP.getCode()) && null != temp.getLimitTimeLength()) {
                    if (temp.getLimitTimeLength() > 0) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.error("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }
            //寻找    按次数类型VIP
            for (int i = 0; i < list.size(); i++) {
                AccountVipInfoEntity temp = list.get(i);
                if (temp.getVipType().equals(AccountVipInfoTypeEnum.VIP.getCode()) && null != temp.getLimitTimes()) {
                    if (temp.getLimitTimes() > 0) {
                        this.updateAccountVipUseing(temp.getAccountVipId());
                        logger.error("#1[处理最高等级的会员权益标识]-[结束]-accountVipInfo={}", temp);
                        return temp;
                    } else {
                        this.updateAccountVipStatusEnd(temp.getAccountVipId());
                        break;
                    }
                }
            }
        }

        return null;
    }


    //-------------------------------超级分割线---------------------------------
    //更新用户权益为当前使用
    private void updateAccountVipUseing(String accountVipId) {
        AccountVipInfoEntity vipInfoUpdate = new AccountVipInfoEntity();
        vipInfoUpdate.setAccountVipId(accountVipId);
        vipInfoUpdate.setIsUseing(BooleanEnum.YES.getCode());
        vipInfoMapper.updateVipInfo(vipInfoUpdate);
    }

    //更新用户权益为结束状态
    private void updateAccountVipStatusEnd(String accountVipId) {
        AccountVipInfoEntity vipInfoUpdate = new AccountVipInfoEntity();
        vipInfoUpdate.setAccountVipId(accountVipId);
        vipInfoUpdate.setStatus(AccountVipInfoStatusEnum.END.getCode());
        vipInfoUpdate.setIsUseing(BooleanEnum.NO.getCode());
        vipInfoMapper.updateVipInfo(vipInfoUpdate);
    }
}
