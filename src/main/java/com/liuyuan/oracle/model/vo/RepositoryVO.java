package com.liuyuan.oracle.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 仓库视图（脱敏）
 */
@Data
public class RepositoryVO implements Serializable {

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
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 仓库主要使用的编程语言
     */
    private String languageName;

    /**
     * 仓库的星标数
     */
    private Integer stars;

    /**
     * 仓库的分支数
     */
    private Integer forks;

    /**
     * 仓库的README文件内容
     */
    private String readme;

    /**
     * 开源协议的名称
     */
    private String licenseName;

    /**
     * 开源协议的URL地址
     */
    private String licenseUrl;

    private static final long serialVersionUID = 1L;
}
