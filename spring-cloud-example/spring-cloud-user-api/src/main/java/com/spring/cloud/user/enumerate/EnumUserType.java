package com.spring.cloud.user.enumerate;

/**
 * 用户类型
 *
 * @author Travel Hu
 */
public enum EnumUserType {
    GENERAL_USER(1,"普通用户"),
    ADVANCED_USER(2,"高级用户");



    private final Integer type;
    private final String description;


    EnumUserType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
