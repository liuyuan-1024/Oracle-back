package com.liuyuan.oracle.model.dto.language;

import lombok.Data;

import java.io.Serializable;

/**
 * 编程语言创建请求体
 */
@Data
public class LanguageAddRequest implements Serializable {

    /**
     * 编程语言标签名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
