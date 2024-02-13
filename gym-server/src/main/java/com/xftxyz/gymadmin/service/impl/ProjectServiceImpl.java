package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Project;
import com.xftxyz.gymadmin.service.ProjectService;
import com.xftxyz.gymadmin.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【project(项目信息)】的数据库操作Service实现
* @createDate 2024-02-14 07:08:06
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

}




