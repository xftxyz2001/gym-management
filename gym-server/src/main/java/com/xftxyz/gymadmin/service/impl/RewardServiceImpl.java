package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Reward;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.RewardMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.RewardService;
import com.xftxyz.gymadmin.vo.req.ListRewardReq;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【reward(奖励信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:07
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward>
        implements RewardService {

    @Override
    public Boolean saveReward(Reward reward) {
        LambdaQueryWrapper<Reward> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Reward::getName, reward.getName());
        Reward existReward = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existReward)) {
            throw new BusinessException(ResultEnum.REWARD_EXIST);
        }
        if (baseMapper.insert(reward) <= 0) {
            throw new BusinessException(ResultEnum.REWARD_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeReward(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.REWARD_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeRewards(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) <= idList.size()) {
            throw new BusinessException(ResultEnum.REWARD_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateReward(Reward reward) {
        Reward existReward = baseMapper.selectById(reward.getId());
        if (ObjectUtils.isEmpty(existReward)) {
            throw new BusinessException(ResultEnum.REWARD_NOT_EXIST);
        }
        if (baseMapper.updateById(reward) <= 0) {
            throw new BusinessException(ResultEnum.REWARD_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Reward getReward(Long id) {
        Reward reward = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(reward)) {
            throw new BusinessException(ResultEnum.REWARD_NOT_EXIST);
        }
        return reward;
    }

    @Override
    public IPage<Reward> listRewards(ListRewardReq listRewardReq, Integer current, Integer size) {
        LambdaQueryWrapper<Reward> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listRewardReq.getName()), Reward::getName, listRewardReq.getName());

        return baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }
}




