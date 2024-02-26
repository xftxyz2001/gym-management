package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.vo.req.ListConsumeReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【consume(消费记录)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface ConsumeService extends IService<Consume> {

    Boolean saveConsume(Consume consume);

    Boolean removeConsume(Long id);

    Boolean removeConsumes(List<Long> idList);

    Boolean updateConsume(Consume consume);

    Consume getConsume(Long id);

    IPage<Consume> listConsumes(ListConsumeReq listConsumeReq, Integer current, Integer size);
}
