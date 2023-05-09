package com.liuyuan.oracle.model.conversion;

import com.liuyuan.oracle.model.entity.Language;
import com.liuyuan.oracle.model.entity.License;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.vo.RepositoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 仓库实体转换器接口
 */
@Mapper
public interface RepositoryConvert {
    RepositoryConvert INSTANCE = Mappers.getMapper(RepositoryConvert.class);


    // region to VO

    @Mapping(target = "name", source = "repository.name")
    @Mapping(target = "description", source = "repository.description")
    @Mapping(target = "createdAt", source = "repository.createdAt")
    @Mapping(target = "updatedAt", source = "repository.updatedAt")
    @Mapping(target = "languageName", source = "language.name")
    @Mapping(target = "licenseName", source = "license.name")
    @Mapping(target = "licenseUrl", source = "license.url")
    RepositoryVO toRepositoryVo(Repository repository, Language language, License license);

    // endregion
}
