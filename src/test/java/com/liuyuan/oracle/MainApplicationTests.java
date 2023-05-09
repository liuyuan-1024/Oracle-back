package com.liuyuan.oracle;

import com.liuyuan.oracle.model.conversion.RepositoryConvert;
import com.liuyuan.oracle.model.conversion.UserConvert;
import com.liuyuan.oracle.model.entity.Language;
import com.liuyuan.oracle.model.entity.License;
import com.liuyuan.oracle.model.entity.Repository;
import com.liuyuan.oracle.model.entity.User;
import com.liuyuan.oracle.model.vo.LoginUserVO;
import com.liuyuan.oracle.model.vo.RepositoryVO;
import com.liuyuan.oracle.model.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 */
@SpringBootTest
class MainApplicationTests {

    @Test
    void userConvertTest() {

        User user = new User();
        user.setId(1L);
        user.setEmail("1922381022");
        user.setPassword("1234456677");
        user.setUnionId("union id");
        user.setMpOpenId("mp open id");
        user.setNickName("Oracle");
        user.setUserAvatar("头像");
        user.setUserProfile("用户简介");
        user.setUserRole("用户角色");
        System.out.println("user = " + user);

        UserVO userVO = UserConvert.INSTANCE.toUserVo(user);
        System.out.println("userVO = " + userVO);
        LoginUserVO loginUserVO = UserConvert.INSTANCE.toLoginUserVo(user);
        System.out.println("loginUserVO = " + loginUserVO);
    }

    @Test
    void repositoryConvertTest() {

        Repository repository = new Repository();
        repository.setId(1L);
        repository.setName("Oracle");
        repository.setDescription("我的第一个app");
        repository.setOwnerId(1L);
        repository.setLanguageId(33);
        repository.setStars(199);
        repository.setForks(300);
        repository.setReadme("README");
        repository.setLicenseId(88);
        System.out.println("repository = " + repository);

        Language language = new Language();
        language.setId(123);
        language.setName("Java");

        License license = new License();
        license.setId(585);
        license.setName("GPL");
        license.setUrl("www.gpl.com");
        license.setDescription("必须开源");

        RepositoryVO repositoryVO = RepositoryConvert.INSTANCE.toRepositoryVo(repository, language, license);
        System.out.println("repositoryVO = " + repositoryVO);
    }

}
