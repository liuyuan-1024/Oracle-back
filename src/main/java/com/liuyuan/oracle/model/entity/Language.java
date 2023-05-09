package com.liuyuan.oracle.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 编程语言标签表
 *
 * @TableName language
 */
@TableName(value = "language")
@Data
public class Language implements Serializable {
    /**
     * 编程语言标签ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编程语言标签名称
     */
    private String name;

    /**
     * 创建人
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 是否已删除(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}