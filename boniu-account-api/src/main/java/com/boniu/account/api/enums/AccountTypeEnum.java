package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @EnumName AccountTypeEnum
 * @Description 账户类型枚举类
 * @Author HanXin
 * @Date 2019-07-11
 */

public enum AccountTypeEnum {
    NORMAL("NORMAL", "普通用户"),
    VIP("VIP", "会员用户");

    private String code;
    private String desc;

    AccountTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountTypeEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountTypeEnum accountTypeEnum : values()) {
            if (code.equals(accountTypeEnum.getCode())) {
                return accountTypeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
