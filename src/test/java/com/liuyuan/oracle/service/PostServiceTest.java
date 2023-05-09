package com.liuyuan.oracle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyuan.oracle.model.dto.reference.post.PostQueryRequest;

import javax.annotation.Resource;

import com.liuyuan.oracle.model.entity.reference.Post;
import com.liuyuan.oracle.service.reference.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;

    @Test
    void searchFromEs() {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(1L);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        Assertions.assertNotNull(postPage);
    }

}