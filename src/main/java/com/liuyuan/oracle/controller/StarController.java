package com.liuyuan.oracle.controller;

import com.liuyuan.oracle.common.response.BaseResponse;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.common.response.ResultUtils;
import com.liuyuan.oracle.exception.BusinessException;
import com.liuyuan.oracle.model.dto.star.StarRequest;
import com.liuyuan.oracle.model.entity.User;
import com.liuyuan.oracle.service.StarService;
import com.liuyuan.oracle.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 仓库点赞接口
 */
@RestController
@RequestMapping("/star")
public class StarController {

    @Resource
    private UserService userService;

    @Resource
    private StarService starService;

    /**
     * 点赞 / 取消点赞
     *
     * @param starRequest 仓库 点赞/取消点赞 请求体
     * @param request     http请求
     * @return 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Integer> doStar(@RequestBody StarRequest starRequest, HttpServletRequest request) {

        if (starRequest == null || starRequest.getRepositoryId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        Long repositoryId = starRequest.getRepositoryId();
        int result = starService.doStar(repositoryId, loginUser);
        return ResultUtils.success(result);
    }

}
