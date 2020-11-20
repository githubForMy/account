package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @EnumName AccountTypeEnum
 * @Description 账户会员信息类型枚举类
 * @Author HanXin
 * @Date 2019-07-11
 */

public enum AccountVipInfoTypeEnum {
    NORMAL("NORMAL", "普通用户"),
    VIP("VIP", "普通会员用户"),
    SVIP("SVIP", "超级会员用户");

    private String code;
    private String desc;

    AccountVipInfoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountVipInfoTypeEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountVipInfoTypeEnum accountVipInfoTypeEnum : values()) {
            if (code.equals(accountVipInfoTypeEnum.getCode())) {
                return accountVipInfoTypeEnum;
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
