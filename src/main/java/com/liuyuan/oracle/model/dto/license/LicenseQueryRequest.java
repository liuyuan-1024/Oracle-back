package com.liuyuan.oracle.model.dto.license;

import com.baomidou.mybatisplus.annotation.TableField;
import com.liuyuan.oracle.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 开源许可证查询请求体(仅限管理员)
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LicenseQueryRequest extends PageRequest implements Serializable {

    /**
     * 许可证ID
     */
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
