package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.CardType;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【card_type(卡类型信息)】的数据库操作Service
 * @createDate 2024-03-01 23:25:02
 */
public interface CardTypeService extends IService<CardType> {

    Boolean saveCardType(CardType cardType);

    Boolean removeCardType(Long id);

    Boolean removeCardTypes(List<Long> idList);

    Boolean updateCardType(CardType cardType);

    CardType getCardType(Long id);

    IPage<CardType> listCardTypes(Integer current, Integer size);

    List<CardType> listCardTypesByName(String name);
}
