package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.CoachMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CoachService;
import com.xftxyz.gymadmin.vo.req.ListCoachReq;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【coach(教练信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach>
        implements CoachService {

    @Override
    public Boolean saveCoach(Coach coach) {
        LambdaQueryWrapper<Coach> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Coach::getName, coach.getName());
        Coach existCoach = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCoach)) {
            throw new BusinessException(ResultEnum.COACH_EXIST);
        }
        if (baseMapper.insert(coach) <= 0) {
            throw new BusinessException(ResultEnum.COACH_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCoach(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.COACH_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCoaches(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.COACH_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateCoach(Coach coach) {
        Coach existCoach = baseMapper.selectById(coach.getId());
        if (ObjectUtils.isEmpty(existCoach)) {
            throw new BusinessException(ResultEnum.COACH_NOT_EXIST);
        }
        if (baseMapper.updateById(coach) <= 0) {
            throw new BusinessException(ResultEnum.COACH_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Coach getCoach(Long id) {
        Coach coach = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(coach)) {
            throw new BusinessException(ResultEnum.COACH_NOT_EXIST);
        }
        return coach;
    }

    @Override
    public IPage<Coach> listCoaches(ListCoachReq listCoachReq, Integer current, Integer size) {
        LambdaQueryWrapper<Coach> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCoachReq.getName()), Coach::getName, listCoachReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCoachReq.getSkill()), Coach::getSkill, listCoachReq.getSkill());
        return baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }

    @Override
    public List<Coach> listCoachsByName(String name) {
        LambdaQueryWrapper<Coach> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Coach::getName, name);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}




