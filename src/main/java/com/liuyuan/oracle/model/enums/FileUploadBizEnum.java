package com.liuyuan.oracle.model.enums;

import cn.hutool.core.io.FileUtil;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传业务类型枚举
 * 文件大小单位: 字节Byte
 * 8bit = 1Byte 1024Byte=1KB
 */
public enum FileUploadBizEnum {

    USER_AVATAR("用户头像", "user_avatar", 2 ^ 20),

    USER_REPOSITORY("用户仓库", "user_repository", 50 * 2 ^ 20);

    private final String text;

    private final String value;

    // 文件尺寸最大值 -1不设限
    private final long maxSize;

    FileUploadBizEnum(String text, String value, long maxSize) {
        this.text = text;
        this.value = value;
        this.maxSize = maxSize;
    }

    /**
     * 获取value列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     */
    public static FileUploadBizEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (FileUploadBizEnum anEnum : FileUploadBizEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * @param file              文件
     * @param fileUploadBizEnum 文件上传枚举
     * @return 符合(true) 不符合(false)
     */
    public void verifyFile(MultipartFile file, FileUploadBizEnum fileUploadBizEnum) {
        // 1.校验文件大小
        if (file.getSize() > fileUploadBizEnum.maxSize) {
            long max = fileUploadBizEnum.maxSize / 2 ^ 20;
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过" + max + "MB");
        }

        // 2.校验上传文件的业务类型
        switch (fileUploadBizEnum.getValue()) {
            case ("user_avatar"):
                verifyAvatar(file);
                break;
            case ("user_repository"):
                verifyRepository();
                break;
            default:
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "业务类型错误");
        }
    }

    /**
     * 校验头像
     */
    private void verifyAvatar(MultipartFile avatar) {

        String suffix = FileUtil.getSuffix(avatar.getOriginalFilename());

        if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(suffix)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件类型错误");
        }
    }

    /**
     * 校验仓库
     */
    private void verifyRepository() {

    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public long getMaxSize() {
        return maxSize;
    }
}
