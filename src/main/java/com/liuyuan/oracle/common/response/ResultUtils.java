package com.liuyuan.oracle.common.response;

/**
 * 响应结果工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param <T>  数据的泛型
     * @param data 待返回的数据
     * @return 统一响应类对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ErrorCode.SUCCESS.getCode(), data, ErrorCode.SUCCESS.getMessage());
    }

    /**
     * 失败
     *
     * @param errorCode 失败码
     * @return 统一响应类对象
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param errorCode 失败码
     * @param message   自定义消息
     * @return 统一响应类对象
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }

    /**
     * 失败 todo 开发完成后删去
     * 一般不允许返回自定义code, 故测试时可以使用这个方法，上线后此方法应当删除
     *
     * @param code    自定义code
     * @param message 自定义消息
     * @return 统一响应类对象
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }
}
