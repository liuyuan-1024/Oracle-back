package com.liuyuan.oracle.model.dto.license;

import java.io.Serializable;

/**
 * 开源许可证创建请求体(仅限管理员)
 */
public class LicenseAddRequest implements Serializable {

    /**
     * 许可证名称
     */
    private String name;

    /**
     * 许可证文本的URL地址
     */
    private String url;

    /**
     * 许可证的描述信息
     */
    private String description;

    private static final long serialVersionUID = 1L;
}
