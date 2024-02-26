package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Reward;
import com.xftxyz.gymadmin.vo.req.ListRewardReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【reward(奖励信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:07
 */
public interface RewardService extends IService<Reward> {

    Boolean saveReward(Reward reward);

    Boolean removeReward(Long id);

    Boolean removeRewards(List<Long> idList);

    Boolean updateReward(Reward reward);

    Reward getReward(Long id);

    IPage<Reward> listRewards(ListRewardReq listRewardReq, Integer current, Integer size);
}
