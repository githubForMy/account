package com.boniu.account.api.enums;

import com.boniu.base.utile.tool.StringUtil;

/**
 * @Description 账户注销申请状态
 * @Author hanxin
 * @Date 2020/10/29
 */

public enum AccountCancelApplyStatusEnum {
    INIT("INIT", "初始化申请"),
    AUDITING("AUDITING", "审核中"),
    CANCEL("CANCEL", "注销完成"),
    FAILURE("FAILURE", "失败");

    private String code;
    private String desc;

    AccountCancelApplyStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountCancelApplyStatusEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (AccountCancelApplyStatusEnum accountCancelApplyStatusEnum : values()) {
            if (code.equals(accountCancelApplyStatusEnum.getCode())) {
                return accountCancelApplyStatusEnum;
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
