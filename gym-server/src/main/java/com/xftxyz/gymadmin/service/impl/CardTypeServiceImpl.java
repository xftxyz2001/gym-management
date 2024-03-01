package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.CardType;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.CardTypeMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CardTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【card_type(卡类型信息)】的数据库操作Service实现
 * @createDate 2024-03-01 23:25:02
 */
@Service
public class CardTypeServiceImpl extends ServiceImpl<CardTypeMapper, CardType>
        implements CardTypeService {

    @Override
    public Boolean saveCardType(CardType cardType) {
        LambdaQueryWrapper<CardType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CardType::getName, cardType.getName());
        CardType existCardType = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCardType)) {
            throw new BusinessException(ResultEnum.CARD_TYPE_EXIST);
        }
        if (baseMapper.insert(cardType) <= 0) {
            throw new BusinessException(ResultEnum.CARD_TYPE_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCardType(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.CARD_TYPE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCardTypes(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.CARD_TYPE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateCardType(CardType cardType) {
        CardType existCardType = baseMapper.selectById(cardType.getId());
        if (ObjectUtils.isEmpty(existCardType)) {
            throw new BusinessException(ResultEnum.CARD_TYPE_NOT_EXIST);
        }
        if (baseMapper.updateById(cardType) <= 0) {
            throw new BusinessException(ResultEnum.CARD_TYPE_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public CardType getCardType(Long id) {
        CardType cardType = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(cardType)) {
            throw new BusinessException(ResultEnum.CARD_TYPE_NOT_EXIST);
        }
        return cardType;
    }

    @Override
    public IPage<CardType> listCardTypes(Integer current, Integer size) {
        return baseMapper.selectPage(new Page<>(current, size), null);
    }
}




