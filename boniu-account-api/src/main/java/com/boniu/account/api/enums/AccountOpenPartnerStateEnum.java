package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @ClassName AccountOpenPartnerState
 * @Description 开放平台合作方状态
 * @Author HanXin
 * @Date 2020-06-12
 */

public enum AccountOpenPartnerStateEnum {
    NORMAL("NORMAL", "正常"),
    CANCEL("CANCEL", "注销");

    private String code;
    private String desc;

    AccountOpenPartnerStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountOpenPartnerStateEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountOpenPartnerStateEnum accountOpenPartnerStateEnum : values()) {
            if (code.equals(accountOpenPartnerStateEnum.getCode())) {
                return accountOpenPartnerStateEnum;
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
