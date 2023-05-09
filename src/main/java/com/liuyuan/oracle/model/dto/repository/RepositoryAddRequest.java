package com.liuyuan.oracle.model.dto.repository;

import java.io.Serializable;

/**
 * 仓库创建请求体
 */
public class RepositoryAddRequest implements Serializable {

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库的描述信息
     */
    private String description;

    /**
     * 仓库主要使用的编程语言
     * todo 如何自动识别仓库主要使用的编程语言
     */
    private Integer languageId;

    /**
     * 仓库的README文件内容
     */
    private String readme;

    /**
     * 开源许可证类型
     */
    private Integer licenseId;

    private static final long serialVersionUID = 1L;
}
