package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Exchange;
import com.xftxyz.gymadmin.vo.req.ExchangeReq;
import com.xftxyz.gymadmin.vo.req.ListExchangeReq;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【exchange(积分兑换记录)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface ExchangeService extends IService<Exchange> {

    Boolean exchangeReward(ExchangeReq exchangeReq);

    Boolean saveExchange(Exchange exchange);

    Boolean removeExchange(Long id);

    Boolean removeExchanges(List<Long> idList);

    Exchange getExchange(Long id);

    IPage<Exchange> listExchanges(ListExchangeReq listExchangeReq, Integer current, Integer size);

    Boolean updateExchange(Exchange exchange);
}
