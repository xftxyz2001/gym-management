package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Card;
import com.xftxyz.gymadmin.domain.CardType;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.helper.DateHelper;
import com.xftxyz.gymadmin.mapper.CardMapper;
import com.xftxyz.gymadmin.mapper.CardTypeMapper;
import com.xftxyz.gymadmin.mapper.ConsumeMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.vo.req.ListCardReq;
import com.xftxyz.gymadmin.vo.req.RegisterReq;
import com.xftxyz.gymadmin.vo.resp.MemberLoginResp;
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
    private final ConsumeMapper consumeMapper;

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
        return this.cardSetMemberAndCardType(card);
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
        Page<Card> cardPage = baseMapper.selectPage(new Page<>(current, size), cardLambdaQueryWrapper);
        cardPage.getRecords().forEach(this::cardSetMemberAndCardType);
        return cardPage;
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

        // 保存会员卡
        Card card = new Card();
        card.setMemberId(memberId);
        card.setCardType(cardTypeId);
        card.setValidTime(DateHelper.getAfterDays(new Date(), cardType.getValidTime()));
        card.setTotal(cardType.getCount());
        card.setRemain(cardType.getCount());
        card.setStatus(Card.STATUS_NORMAL);
        if (baseMapper.insert(card) <= 0) {
            throw new BusinessException(ResultEnum.CARD_SAVE_FAILED);
        }

        // 保存消费记录
        Consume consume = new Consume();
        consume.setMemberId(memberId);
        consume.setCtype(Consume.CTYPE_CARD);
        consume.setCitem(cardTypeId);
        consume.setPayType(registerReq.getPayType());
        consume.setAmount(cardType.getPrice());
        consume.setStatus(Consume.STATUS_PAID);
        if (consumeMapper.insert(consume) <= 0) {
            throw new BusinessException(ResultEnum.CONSUME_SAVE_FAILED);
        }

        // 更新会员积分
        member.setPoints(member.getPoints() + consume.getAmount().intValue());
        if (memberMapper.updateById(member) <= 0) {
            throw new BusinessException(ResultEnum.MEMBER_UPDATE_FAILED);
        }
        return true;
    }

    private Card cardSetMemberAndCardType(Card card) {
        if (!ObjectUtils.isEmpty(card.getMemberId())) {
            Member member = memberMapper.selectById(card.getMemberId());
            if (!ObjectUtils.isEmpty(member)) {
                card.setMember(member);
            }
        }
        if (!ObjectUtils.isEmpty(card.getCardType())) {
            CardType cardType = cardTypeMapper.selectById(card.getCardType());
            if (!ObjectUtils.isEmpty(cardType)) {
                card.setCardTypeEntity(cardType);
            }
        }
        return card;
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
        cards.sort(Comparator.comparing(Card::getValidTime));

        for (Card card : cards) {
            if (card.getStatus() != Card.STATUS_NORMAL) {
                continue;
            }
            if (card.getRemain() < 0) {
                return card;
            }
            if (card.getRemain() > 0) {
                card.setRemain(card.getRemain() - 1);
                if (baseMapper.updateById(card) <= 0) {
                    throw new BusinessException(ResultEnum.CARD_UPDATE_FAILED);
                }
                return card;
            }
        }
        Card last = cards.getLast();
        return this.cardSetMemberAndCardType(last);
    }

    @Override
    public void checkCardStatus() {
        // 将所有过期的会员卡状态设置为过期
        LambdaUpdateWrapper<Card> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.lt(Card::getValidTime, new Date());
        lambdaUpdateWrapper.set(Card::getStatus, Card.STATUS_EXPIRED);
        baseMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public MemberLoginResp login(Long id) {
        Card card = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(card)) {
            throw new BusinessException(ResultEnum.CARD_NOT_EXIST);
        }
        Member member = memberMapper.selectById(card.getMemberId());
        // if (ObjectUtils.isEmpty(member)) {
        //     throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        // }
        CardType cardType = cardTypeMapper.selectById(card.getCardType());
        // if (ObjectUtils.isEmpty(cardType)) {
        //     throw new BusinessException(ResultEnum.CARD_TYPE_NOT_EXIST);
        // }
        MemberLoginResp memberLoginResp = new MemberLoginResp();
        memberLoginResp.setCardType(cardType.getName());
        memberLoginResp.setValidTime(card.getValidTime());
        memberLoginResp.setRemain(card.getRemain());
        memberLoginResp.setStatus(card.getStatus());
        memberLoginResp.setMemberId(member.getId());
        memberLoginResp.setName(member.getName());
        memberLoginResp.setGender(member.getGender());
        memberLoginResp.setBirthday(member.getBirthday());
        memberLoginResp.setHeight(member.getHeight());
        memberLoginResp.setWeight(member.getWeight());
        memberLoginResp.setBodyType(member.getBodyType());
        memberLoginResp.setContact(member.getContact());
        memberLoginResp.setAddress(member.getAddress());
        memberLoginResp.setPoints(member.getPoints());

        return memberLoginResp;
    }

    @Override
    public Card getOneCard(String symbol) {
        try {
            return this.getCard(Long.valueOf(symbol));
        } catch (NumberFormatException | BusinessException e) {
            return this.getCardByContact(symbol);
        }
    }
}




