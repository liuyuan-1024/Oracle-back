package com.liuyuan.oracle.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyuan.oracle.model.dto.user.UserQueryRequest;
import com.liuyuan.oracle.model.entity.User;
import com.liuyuan.oracle.model.vo.LoginUserVO;
import com.liuyuan.oracle.model.vo.UserVO;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 源
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-05-06 02:58:49
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param email         用户邮箱
     * @param password      用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String email, String password, String checkPassword);

    /**
     * 用户登录
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @param request  请求
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String email, String password, HttpServletRequest request);

    /**
     * 用户登录（微信开放平台）
     *
     * @param wxOAuth2UserInfo 从微信获取的用户信息
     * @param request          请求
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLoginByMpOpen(WxOAuth2UserInfo wxOAuth2UserInfo, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request 请求
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request 请求
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user 用户
     * @return
     */
    boolean isAdmin(User user);

    /**
     * 是否被封禁
     *
     * @param user 用户
     * @return
     */
    boolean isBan(User user);

    /**
     * 用户注销
     *
     * @param request 请求
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param user 用户
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList 用户列表
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest 用户查询请求体
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
