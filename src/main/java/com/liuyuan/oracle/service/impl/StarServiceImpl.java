package com.liuyuan.oracle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.exception.BusinessException;
import com.liuyuan.oracle.mapper.StarMapper;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.entity.Star;
import com.liuyuan.oracle.model.entity.User;
import com.liuyuan.oracle.service.RepositoryService;
import com.liuyuan.oracle.service.StarService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 源
 * @description 针对表【star(仓库点赞表)】的数据库操作Service实现
 * @createDate 2023-05-07 17:58:28
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public int doStar(long repositoryId, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        Repository repository = repositoryService.getById(repositoryId);
        if (repository == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已点赞
        long userId = loginUser.getId();
        // 每个用户串行点赞
        // 锁必须要包裹住事务方法
        StarService starService = (StarService) AopContext.currentProxy();
        synchronized (String.valueOf(userId).intern()) {
            return starService.doStarInner(repositoryId, userId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doStarInner(long repositoryId, long userId) {
        Star star = new Star();
        star.setUserId(userId);
        star.setRepositoryId(repositoryId);
        QueryWrapper<Star> queryWrapper = new QueryWrapper<>(star);
        Star oldStar = this.getOne(queryWrapper);

        boolean result;
        // 已点赞
        if (oldStar != null) {
            result = this.remove(queryWrapper);
            if (result) {
                // 点赞数 - 1
                result = repositoryService.update()
                    .eq("id", repositoryId)
                    .gt("stars", 0)
                    .setSql("stars = stars - 1")
                    .update();
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        } else {
            // 未点赞
            result = this.save(star);
            if (result) {
                // 点赞数 + 1
                result = repositoryService.update()
                    .eq("id", repositoryId)
                    .setSql("stars = stars + 1")
                    .update();
                return result ? 1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
    }
}




