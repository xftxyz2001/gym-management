package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.CardMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.vo.req.ListCardReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【card(会员卡信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl extends ServiceImpl<CardMapper, Card>
        implements CardService {

    private final MemberMapper memberMapper;

    @Override
    public Boolean saveCard(Card card) {
        if (baseMapper.insert(card) <= 0) {
            throw new BusinessException(ResultEnum.CARD_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCard(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.CARD_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCards(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.CARD_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateCard(Card card) {
        Card existCard = baseMapper.selectById(card.getId());
        if (ObjectUtils.isEmpty(existCard)) {
            throw new BusinessException(ResultEnum.CARD_NOT_EXIST);
        }
        if (baseMapper.updateById(card) <= 0) {
            throw new BusinessException(ResultEnum.CARD_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Card getCard(Long id) {
        Card card = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(card)) {
            throw new BusinessException(ResultEnum.CARD_NOT_EXIST);
        }
        return card;
    }

    @Override
    public IPage<Card> listCards(ListCardReq listCardReq, Integer current, Integer size) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCardReq.getName()), Member::getName, listCardReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCardReq.getContact()), Member::getContact, listCardReq.getContact());
        List<Member> members = memberMapper.selectList(lambdaQueryWrapper);
        List<Long> memberIdList = members.stream().map(Member::getId).toList();

        LambdaQueryWrapper<Card> cardLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cardLambdaQueryWrapper.in(!ObjectUtils.isEmpty(memberIdList), Card::getMemberId, memberIdList);
        return baseMapper.selectPage(new Page<>(current, size), cardLambdaQueryWrapper);
    }
}




