package com.liuyuan.oracle.aop;

import com.liuyuan.oracle.annotation.AuthCheck;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.exception.BusinessException;
import com.liuyuan.oracle.model.enums.UserRoleEnum;
import com.liuyuan.oracle.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验 AOP
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck AuthCheck注解对象
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 当前登录用户的权限的枚举对象
        UserRoleEnum loginUserRoleEnum = UserRoleEnum.getEnumByRole(userService.getLoginUser(request).getUserRole());

        // 用户权限不存在 或 用户被封号，直接拒绝
        if (ObjectUtils.isEmpty(loginUserRoleEnum) || UserRoleEnum.BAN.equals(loginUserRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        // 目标权限的枚举对象
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByRole(authCheck.mustRole());

        // 必需权限存在时
        if (ObjectUtils.isNotEmpty(mustRoleEnum)) {
            // 如果 userRole的优先级低于mustRole的 则抛出异常
            if (!UserRoleEnum.isPriority(loginUserRoleEnum, mustRoleEnum)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }

        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

