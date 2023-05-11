package com.liuyuan.oracle.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyuan.oracle.model.dto.repository.RepositoryQueryRequest;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.vo.RepositoryVO;

import java.util.List;

/**
 * @author 源
 * @description 针对表【repository(代码仓库表)】的数据库操作Service
 * @createDate 2023-05-07 17:57:21
 */
public interface RepositoryService extends IService<Repository> {

    /**
     * 校验
     *
     * @param repository 仓库
     * @param add        是否为仓库创建请求
     */
    void verifyRepository(Repository repository, boolean add);

    /**
     * 获取脱敏的仓库信息
     *
     * @param repository 仓库
     * @return
     */
    RepositoryVO getRepositoryVO(Repository repository);

    /**
     * 获取脱敏的仓库列表信息
     *
     * @param repositoryList 仓库列表
     * @return
     */
    List<RepositoryVO> getRepositoryVO(List<Repository> repositoryList);


    /**
     * 获取查询条件
     *
     * @param repositoryQueryRequest 仓库查询请求体
     * @return
     */
    QueryWrapper<Repository> getQueryWrapper(RepositoryQueryRequest repositoryQueryRequest);
}
