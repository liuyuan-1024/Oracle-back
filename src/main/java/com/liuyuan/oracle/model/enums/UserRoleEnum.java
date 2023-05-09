package com.liuyuan.oracle.model.enums;

import com.liuyuan.oracle.constant.UserConstant;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 用户角色枚举
 * 角色权限优先级: 数字越小, 优先级越高
 */
public enum UserRoleEnum {

    ADMIN("管理员", UserConstant.ADMIN_ROLE, 1),
    USER("用户", UserConstant.DEFAULT_ROLE, 2),

    BAN("被封号", UserConstant.BAN_ROLE, 3);

    private final String text;
    private final String role;
    private final Integer value;

    UserRoleEnum(String text, String role, int value) {
        this.text = text;
        this.role = role;
        this.value = value;
    }

    public String getRole() {
        return role;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    /**
     * 根据 role 获取枚举
     *
     * @param role 具体权限
     * @return 对应的用户角色枚举类对象
     */
    public static UserRoleEnum getEnumByRole(String role) {
        if (ObjectUtils.isEmpty(role)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.role.equals(role)) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 判断当前权限是否优先于必需权限
     *
     * @param role     当前权限
     * @param mustRole 必需权限
     * @return true or false
     */
    public static boolean isPriority(UserRoleEnum role, UserRoleEnum mustRole) {
        return role.getValue() - mustRole.getValue() <= 0;
    }

}
