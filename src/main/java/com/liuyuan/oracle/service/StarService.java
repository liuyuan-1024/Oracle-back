package com.liuyuan.oracle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyuan.oracle.model.entity.Star;
import com.liuyuan.oracle.model.entity.User;

/**
 * @author 源
 * @description 针对表【star(仓库点赞表)】的数据库操作Service
 * @createDate 2023-05-07 17:58:28
 */
public interface StarService extends IService<Star> {

    /**
     * 点赞
     *
     * @param repositoryId 仓库id
     * @param loginUser    当前登录用户
     * @return 本次点赞变化数
     */
    int doStar(long repositoryId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param repositoryId 仓库id
     * @param userId       当前登录用户
     * @return 本次点赞变化数
     */
    int doStarInner(long repositoryId, long userId);

}
