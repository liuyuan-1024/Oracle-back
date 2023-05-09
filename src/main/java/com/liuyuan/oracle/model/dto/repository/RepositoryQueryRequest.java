package com.liuyuan.oracle.model.dto.repository;

import com.liuyuan.oracle.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库查询请求体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RepositoryQueryRequest extends PageRequest implements Serializable {

    /**
     * 仓库ID
     */
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库的描述信息
     */
    private String description;

    /**
     * 仓库所有者的ID
     */
    private Long ownerId;

    /**
     * 仓库主要使用的编程语言
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
