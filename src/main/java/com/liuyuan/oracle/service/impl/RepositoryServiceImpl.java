package com.liuyuan.oracle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyuan.oracle.common.response.ErrorCode;
import com.liuyuan.oracle.constant.CommonConstant;
import com.liuyuan.oracle.exception.BusinessException;
import com.liuyuan.oracle.exception.ThrowUtils;
import com.liuyuan.oracle.mapper.RepositoryMapper;
import com.liuyuan.oracle.model.conversion.RepositoryConvert;
import com.liuyuan.oracle.model.dto.repository.RepositoryQueryRequest;
import com.liuyuan.oracle.model.entity.Language;
import com.liuyuan.oracle.model.entity.License;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.vo.RepositoryVO;
import com.liuyuan.oracle.service.LanguageService;
import com.liuyuan.oracle.service.LicenseService;
import com.liuyuan.oracle.service.RepositoryService;
import com.liuyuan.oracle.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 源
 * @description 针对表【repository(代码仓库表)】的数据库操作Service实现
 * @createDate 2023-05-07 17:57:21
 */
@Service
public class RepositoryServiceImpl extends ServiceImpl<RepositoryMapper, Repository> implements RepositoryService {

    @Resource
    private LanguageService languageService;

    @Resource
    private LicenseService licenseService;


    @Override
    public void validRepository(Repository repository, boolean add) {
        if (repository == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 待校验字段
        Long id = repository.getId();
        String name = repository.getName();
        String description = repository.getDescription();
        Long ownerId = repository.getOwnerId();
        Integer languageId = repository.getLanguageId();
        String readme = repository.getReadme();
        Integer licenseId = repository.getLicenseId();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, description, readme), ErrorCode.PARAMS_ERROR);
        }

        // 参数校验
        boolean flag = id == null || ownerId == null || languageId == null || licenseId == null
            || id <= 0 || ownerId <= 0 || languageId <= 0 || licenseId <= 0;
        ThrowUtils.throwIf(flag, ErrorCode.PARAMS_ERROR);

        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "仓库名称过长");
        }
        if (StringUtils.isNotBlank(description) && description.length() > 2048) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "仓库描述过长");
        }
        if (StringUtils.isNotBlank(readme) && readme.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "README过长");
        }
    }

    @Override
    public RepositoryVO getRepositoryVO(Repository repository) {
        if (repository == null) {
            return null;
        }

        // 参数校验
        this.validRepository(repository, false);

        Integer languageId = repository.getLanguageId();
        Integer licenseId = repository.getLicenseId();

        Language language = languageService.getById(languageId);
        License license = licenseService.getById(licenseId);

        return RepositoryConvert.INSTANCE.toRepositoryVo(repository, language, license);
    }

    @Override
    public List<RepositoryVO> getRepositoryVO(List<Repository> repositoryList) {
        if (CollectionUtils.isEmpty(repositoryList)) {
            return new ArrayList<>();
        }
        return repositoryList.stream().map(this::getRepositoryVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Repository> getQueryWrapper(RepositoryQueryRequest repositoryQueryRequest) {

        if (repositoryQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        Long id = repositoryQueryRequest.getId();
        String name = repositoryQueryRequest.getName();
        String description = repositoryQueryRequest.getDescription();
        Long ownerId = repositoryQueryRequest.getOwnerId();
        Integer languageId = repositoryQueryRequest.getLanguageId();
        String readme = repositoryQueryRequest.getReadme();
        Integer licenseId = repositoryQueryRequest.getLicenseId();
        String sortField = repositoryQueryRequest.getSortField();

        boolean isAsc = repositoryQueryRequest.getSortOrder().equals(CommonConstant.SORT_ORDER_ASC);

        QueryWrapper<Repository> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(id != null, Repository::getId, id);
        queryWrapper.lambda().like(StringUtils.isNotBlank(name), Repository::getName, name);
        queryWrapper.lambda().like(StringUtils.isNotBlank(description), Repository::getDescription, description);
        queryWrapper.lambda().eq(ownerId != null, Repository::getOwnerId, ownerId);
        queryWrapper.lambda().eq(languageId != null, Repository::getLanguageId, languageId);
        queryWrapper.lambda().eq(licenseId != null, Repository::getLicenseId, licenseId);
        queryWrapper.lambda().like(StringUtils.isNotBlank(readme), Repository::getReadme, readme);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), isAsc, sortField);

        return queryWrapper;
    }
}




