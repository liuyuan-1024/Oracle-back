package com.liuyuan.oracle.model.dto.star;

import lombok.Data;

import java.io.Serializable;

/**
 * 仓库 点赞/取消点赞 请求体
 */
@Data
public class StarRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 仓库 id
     */
    private Long repositoryId;
}
