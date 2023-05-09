package com.liuyuan.oracle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyuan.oracle.model.entity.License;
import com.liuyuan.oracle.service.LicenseService;
import com.liuyuan.oracle.mapper.LicenseMapper;
import org.springframework.stereotype.Service;

/**
* @author 源
* @description 针对表【license(开源许可证类型表)】的数据库操作Service实现
* @createDate 2023-05-07 17:58:54
*/
@Service
public class LicenseServiceImpl extends ServiceImpl<LicenseMapper, License>
    implements LicenseService{

}




