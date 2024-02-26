package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.vo.req.ListCoachReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【coach(教练信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface CoachService extends IService<Coach> {

    Boolean saveCoach(Coach coach);

    Boolean removeCoach(Long id);

    Boolean removeCoaches(List<Long> idList);

    Boolean updateCoach(Coach coach);

    Coach getCoach(Long id);

    IPage<Coach> listCoaches(ListCoachReq listCoachReq, Integer current, Integer size);
}
