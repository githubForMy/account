package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @Description 用户会员状态类型枚举类
 * @Author hanxin
 * @Date 2020/11/16
 */

public enum AccountVipInfoStatusEnum {
    NORMAL("NORMAL", "正常"),
    END("END", "结束");

    private String code;
    private String desc;

    AccountVipInfoStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountVipInfoStatusEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountVipInfoStatusEnum accountVipInfoStatusEnum : values()) {
            if (code.equals(accountVipInfoStatusEnum.getCode())) {
                return accountVipInfoStatusEnum;
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
    }
}
