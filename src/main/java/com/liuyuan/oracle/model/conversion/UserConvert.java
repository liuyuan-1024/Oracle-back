package com.liuyuan.oracle.model.conversion;

import com.liuyuan.oracle.model.entity.User;
import com.liuyuan.oracle.model.vo.LoginUserVO;
import com.liuyuan.oracle.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体转换器接口
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);


    // region to VO

    UserVO toUserVo(User user);

    LoginUserVO toLoginUserVo(User user);

    // endregion

}
