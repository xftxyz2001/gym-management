package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.domain.CardType;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.helper.DateHelper;
import com.xftxyz.gymadmin.mapper.CardMapper;
import com.xftxyz.gymadmin.mapper.CardTypeMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.vo.req.ListCardReq;
import com.xftxyz.gymadmin.vo.req.RegisterReq;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
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
    private final CardTypeMapper cardTypeMapper;

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

    @Override
    public StatisticsVO cardStatistics(StatisticsVO statisticsVO) {
        LambdaQueryWrapper<Card> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Date from = statisticsVO.getFrom();
        Date to = statisticsVO.getTo();
        if (ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
        } else if (!ObjectUtils.isEmpty(from) && !ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.between(Card::getCreateTime, from, to);
        } else if (!ObjectUtils.isEmpty(from)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(Card::getCreateTime, from);
        } else {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.le(Card::getCreateTime, to);
        }
        Long count = baseMapper.selectCount(lambdaQueryWrapper);
        statisticsVO.setCount(BigDecimal.valueOf(count));
        return statisticsVO;
    }

    @Override
    public Boolean register(RegisterReq registerReq) {
        Long memberId = registerReq.getMemberId();
        Long cardTypeId = registerReq.getCardTypeId();

        Member member = memberMapper.selectById(memberId);
        if (ObjectUtils.isEmpty(member)) {
            throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        }
        CardType cardType = cardTypeMapper.selectById(cardTypeId);
        if (ObjectUtils.isEmpty(cardType)) {
            throw new BusinessException(ResultEnum.CARD_TYPE_NOT_EXIST);
        }
        Card card = new Card();
        card.setMemberId(memberId);
        card.setCardType(cardTypeId);
        card.setValidTime(DateHelper.getAfterDays(new Date(), cardType.getValidTime()));
        card.setTotal(cardType.getCount());
        card.setRemain(cardType.getCount());
        if (baseMapper.insert(card) <= 0) {
            throw new BusinessException(ResultEnum.CARD_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Card getCardByContact(String contact) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Member::getContact, contact);
        Member member = memberMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtils.isEmpty(member)) {
            throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        }
        LambdaQueryWrapper<Card> cardLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cardLambdaQueryWrapper.eq(Card::getMemberId, member.getId());
        List<Card> cards = baseMapper.selectList(cardLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(cards)) {
            throw new BusinessException(ResultEnum.CARD_NOT_EXIST);
        }
        return cards.stream().min(Comparator.comparing(Card::getValidTime)).get();
    }
}




