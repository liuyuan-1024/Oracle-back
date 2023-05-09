package com.liuyuan.oracle.model.dto.language;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyuan.oracle.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 编程语言查询请求体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LanguageQueryRequest extends PageRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
