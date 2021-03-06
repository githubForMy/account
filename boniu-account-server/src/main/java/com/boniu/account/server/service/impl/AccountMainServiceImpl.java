package com.boniu.account.server.service.impl;

import com.boniu.account.api.request.QueryAccountMainDetailRequest;
import com.boniu.account.api.request.UpdateAccountMainRequest;
import com.boniu.account.api.vo.AccountDetailVO;
import com.boniu.account.api.vo.AccountMainVO;
import com.boniu.account.repository.api.AccountMainMapper;
import com.boniu.account.repository.entity.AccountMainEntity;
import com.boniu.account.server.common.AccountErrorEnum;
import com.boniu.account.server.service.AccountMainService;
import com.boniu.base.utile.exception.BaseException;
import com.boniu.base.utile.message.BaseRequest;
import com.boniu.base.utile.tool.DateUtil;
import com.boniu.base.utile.tool.IDUtils;
import com.boniu.base.utile.tool.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AccountMainServiceImpl
 * @Description
 * @Author HanXin
 * @Date 2020-06-08
 */

@Service
public class AccountMainServiceImpl implements AccountMainService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountMainMapper accountMainMapper;
    @Resource
    private AccountServiceImpl accountServiceImpl;

    @Value("${personal.head.image}")
    private String defaultHeadImg;

    @Override
    public AccountMainVO getAccountMainDetail(QueryAccountMainDetailRequest request) {
        String mobile=request.getMobile();
        if(StringUtil.isBlank(request.getMobile())){
            BaseRequest baseRequest=new BaseRequest();
            baseRequest.setAccountId(request.getAccountId());
            baseRequest.setAppName(request.getAppName());
            AccountDetailVO accountInfo = accountServiceImpl.getAccountInfo(baseRequest);
            mobile=accountInfo.getMobile();
        }

        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setMobile(mobile);
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        //???????????????????????????????????????????????????
        if (null == accountMainEntity) {
            accountMainEntity = new AccountMainEntity();
            accountMainEntity.setAccountMainId(IDUtils.createID());
            accountMainEntity.setMobile(mobile);
            accountMainEntity.setNickname("U" + DateUtil.getNowDateString(new Date(), "yyMM") + StringUtil.getRandomCode(5, true, false));
            accountMainEntity.setHeadImg(defaultHeadImg);
            accountMainEntity.setTotalScore(0);
            accountMainEntity.setRemainScore(0);
            accountMainEntity.setCreateTime(new Date());
            int saveNum = accountMainMapper.save(accountMainEntity);
            if (saveNum != 1) {
                logger.error("#1[???????????????????????????]-[??????????????????]-request={}", request);
                throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
            }
        }

        AccountMainVO accountMainVO = new AccountMainVO();
        accountMainVO.setAccountMainId(accountMainEntity.getAccountMainId());
        accountMainVO.setMobile(accountMainEntity.getMobile());
        accountMainVO.setNickname(accountMainEntity.getNickname());
        accountMainVO.setHeadImg(accountMainEntity.getHeadImg());
        accountMainVO.setTotalScore(accountMainEntity.getTotalScore());
        accountMainVO.setRemainScore(accountMainEntity.getRemainScore());
        accountMainVO.setReceivedAccount(accountMainEntity.getReceivedAccount());
        accountMainVO.setRealName(accountMainEntity.getRealName());
        return accountMainVO;
    }

    @Override
    public void updateAccountMain(UpdateAccountMainRequest request) {
        AccountMainEntity accountMainEntityQuery = new AccountMainEntity();
        accountMainEntityQuery.setAccountMainId(request.getAccountMainId());
        AccountMainEntity accountMainEntity = accountMainMapper.selectBy(accountMainEntityQuery);
        if (null == accountMainEntity) {
            logger.error("#1[?????????????????????]-[???????????????]-request={}", request);
            throw new BaseException(AccountErrorEnum.ACCOUNT_NOT_EXIST.getErrorCode());
        }

        AccountMainEntity accountMainEntityUpdate = new AccountMainEntity();
        accountMainEntityUpdate.setMobile(request.getMobile());
        accountMainEntityUpdate.setAccountMainId(request.getAccountMainId());
        accountMainEntityUpdate.setNickname(request.getNickname());
        accountMainEntityUpdate.setHeadImg(request.getHeadImg());
        accountMainEntityUpdate.setTotalScore(request.getTotalScore());
        accountMainEntityUpdate.setRemainScore(request.getRemainScore());
        accountMainEntityUpdate.setReceivedAccount(request.getReceivedAccount());
        accountMainEntityUpdate.setRealName(request.getRealName());
        accountMainEntityUpdate.setUpdateTime(new Date());
        int updateNum = accountMainMapper.update(accountMainEntityUpdate);
        if (updateNum != 1) {
            logger.error("#1[?????????????????????]-[??????????????????]-request={}", request);
            throw new BaseException(AccountErrorEnum.DB_ERROR.getErrorCode());
        }
    }
}
