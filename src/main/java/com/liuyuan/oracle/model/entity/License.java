package com.liuyuan.oracle.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 开源许可证类型表
 *
 * @TableName license
 */
@TableName(value = "license")
@Data
public class License implements Serializable {
    /**
     * 许可证ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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