package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @Description 用户优惠券使用状态枚举
 * @Author hanxin
 * @Date 2020/10/29
 */

public enum AccountCouponStatusEnum {
    USABLE("USABLE", "可用的"),
    USED("USED", "已使用"),
    INVALID("INVALID", "已失效");

    private String code;
    private String desc;

    AccountCouponStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountCouponStatusEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountCouponStatusEnum accountCouponStatusEnum : values()) {
            if (code.equals(accountCouponStatusEnum.getCode())) {
                return accountCouponStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }
}
