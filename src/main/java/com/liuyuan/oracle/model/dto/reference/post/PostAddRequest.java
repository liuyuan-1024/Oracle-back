package com.liuyuan.oracle.model.dto.reference.post;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 创建请求体
 */
@Data
public class PostAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}