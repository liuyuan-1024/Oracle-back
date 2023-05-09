package com.liuyuan.oracle.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyuan.oracle.model.entity.reference.Post;
import com.liuyuan.oracle.model.entity.reference.PostFavour;
import org.apache.ibatis.annotations.Param;

/**
 * @author 源
 * @description 针对表【post_favour(帖子收藏)】的数据库操作Mapper
 * @createDate 2023-05-06 02:59:48
 * @Entity com.liuyuan.oracle.model.entity.reference.PostFavour
 */
public interface PostFavourMapper extends BaseMapper<PostFavour> {
    /**
     * 分页查询收藏帖子列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, @Param(Constants.WRAPPER) Wrapper<Post> queryWrapper,
                                    long favourUserId);
}




