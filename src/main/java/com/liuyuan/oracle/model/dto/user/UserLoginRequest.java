package com.liuyuan.oracle.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {

    private String email;

    private String password;

    private static final long serialVersionUID = 3191241716373120793L;
}
