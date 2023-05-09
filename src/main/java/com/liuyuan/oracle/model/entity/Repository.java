package com.liuyuan.oracle.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 代码仓库表
 *
 * @TableName repository
 */
@TableName(value = "repository")
@Data
public class Repository implements Serializable {
    /**
     * 仓库ID
     */
    @TableId(type = IdType.AUTO)
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
    private Integer languageId;

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
     * 开源许可证类型
     */
    private Integer licenseId;

    /**
     * 是否为私有仓库(0-公开 1-私有)
     */
    private Integer isPrivate;

    /**
     * 是否已删除(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}