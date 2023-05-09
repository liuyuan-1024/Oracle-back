package com.liuyuan.oracle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyuan.oracle.annotation.AuthCheck;
import com.liuyuan.oracle.common.response.BaseResponse;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.common.response.ResultUtils;
import com.liuyuan.oracle.constant.UserConstant;
import com.liuyuan.oracle.exception.BusinessException;
import com.liuyuan.oracle.exception.ThrowUtils;
import com.liuyuan.oracle.model.dto.repository.RepositoryQueryRequest;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.vo.RepositoryVO;
import com.liuyuan.oracle.service.RepositoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓库接口
 */
@RestController
@RequestMapping("/repository")
public class RepositoryController {

    @Resource
    private RepositoryService repositoryService;

    // region 基本查询

    /**
     * 根据 id 获取仓库视图
     *
     * @param id 仓库ID
     * @return 统一响应结果
     */
    @GetMapping("/get/vo")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<RepositoryVO> getRepositoryVOById(Long id) {

        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Repository repository = repositoryService.getById(id);
        return ResultUtils.success(repositoryService.getRepositoryVO(repository));
    }


    /**
     * 分页获取仓库视图列表
     *
     * @param repositoryQueryRequest 用户查询请求体
     * @return 统一响应结果
     */
    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<RepositoryVO>> listRepositoryVOByPage(@RequestBody
                                                                   RepositoryQueryRequest repositoryQueryRequest) {

        if (repositoryQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = repositoryQueryRequest.getCurrent();
        long size = repositoryQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 获取实体
        Page<Repository> repositoryPage = repositoryService.page(new Page<>(current, size),
            repositoryService.getQueryWrapper(repositoryQueryRequest));
        // 实体转为视图
        List<RepositoryVO> repositoryVOList = repositoryService.getRepositoryVO(repositoryPage.getRecords());
        // 分页
        Page<RepositoryVO> repositoryVOPage = new Page<>(current, size, repositoryPage.getTotal());
        repositoryVOPage.setRecords(repositoryVOList);
        return ResultUtils.success(repositoryVOPage);
    }

    // region

}
