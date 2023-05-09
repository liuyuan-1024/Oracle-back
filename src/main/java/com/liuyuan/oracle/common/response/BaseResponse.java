package com.liuyuan.oracle.common.response;

import java.io.Serializable;
import lombok.Data;

/**
 * 统一响应类(通用返回类)
 *
 * @param <T> 数据泛型, 可以返回各种数据
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
