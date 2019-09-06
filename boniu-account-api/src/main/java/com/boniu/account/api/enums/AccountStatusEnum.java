package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @EnumName AccountStatusEnum
 * @Description 账户状态枚举类
 * @Author HanXin
 * @Date 2019-07-11
 */

public enum AccountStatusEnum {
    NORMAL("NORMAL", "正常"),
    FREEZE("FREEZE", "冻结"),
    CANCEL("CANCEL", "注销"),
    VISITOR("VISITOR", "游客");

    private String code;
    private String desc;

    AccountStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountStatusEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountStatusEnum accountStatusEnum : values()) {
            if (code.equals(accountStatusEnum.getCode())) {
                return accountStatusEnum;
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
