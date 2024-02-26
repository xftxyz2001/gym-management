package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Project;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.ProjectMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.ProjectService;
import com.xftxyz.gymadmin.vo.req.ListProjectReq;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【project(项目信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
        implements ProjectService {

    @Override
    public Boolean saveProject(Project project) {
        LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Project::getName, project.getName());
        Project existProject = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existProject)) {
            throw new BusinessException(ResultEnum.PROJECT_EXIST);
        }
        if (baseMapper.insert(project) <= 0) {
            throw new BusinessException(ResultEnum.PROJECT_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeProject(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.PROJECT_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeProjects(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.PROJECT_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateProject(Project project) {
        Project existProject = baseMapper.selectById(project.getId());
        if (ObjectUtils.isEmpty(existProject)) {
            throw new BusinessException(ResultEnum.PROJECT_NOT_EXIST);
        }
        if (baseMapper.updateById(project) <= 0) {
            throw new BusinessException(ResultEnum.PROJECT_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Project getProject(Long id) {
        Project project = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(project)) {
            throw new BusinessException(ResultEnum.PROJECT_NOT_EXIST);
        }
        return project;
    }

    @Override
    public IPage<Project> listProjects(ListProjectReq listProjectReq, Integer current, Integer size) {
        LambdaQueryWrapper<Project> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listProjectReq.getName()), Project::getName, listProjectReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listProjectReq.getDescription()), Project::getDescription, listProjectReq.getDescription());
        return baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }
}




