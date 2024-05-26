package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.vo.req.ListCardReq;
import com.xftxyz.gymadmin.vo.req.RegisterReq;
import com.xftxyz.gymadmin.vo.resp.MemberLoginResp;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【card(会员卡信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface CardService extends IService<Card> {

    Boolean saveCard(Card card);

    Boolean removeCard(Long id);

    Boolean removeCards(List<Long> idList);

    Boolean updateCard(Card card);

    Card getCard(Long id);

    IPage<Card> listCards(ListCardReq listCardReq, Integer current, Integer size);

    StatisticsVO cardStatistics(StatisticsVO statisticsVO);

    Boolean register(RegisterReq registerReq);

    Card getCardByContact(String contact);

    void checkCardStatus();

    MemberLoginResp login(Long id);

    Card getOneCard(String symbol);
}
