package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Project;
import com.xftxyz.gymadmin.vo.req.ListProjectReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【project(项目信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface ProjectService extends IService<Project> {

    Boolean saveProject(Project project);

    Boolean removeProject(Long id);

    Boolean removeProjects(List<Long> idList);

    Boolean updateProject(Project project);

    Project getProject(Long id);

    IPage<Project> listProjects(ListProjectReq listProjectReq, Integer current, Integer size);
}
