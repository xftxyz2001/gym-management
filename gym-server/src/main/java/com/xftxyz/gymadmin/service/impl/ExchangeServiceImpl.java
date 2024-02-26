package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Exchange;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.domain.Reward;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.ExchangeMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.mapper.RewardMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.ExchangeService;
import com.xftxyz.gymadmin.vo.req.ExchangeReq;
import com.xftxyz.gymadmin.vo.req.ListExchangeReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【exchange(积分兑换记录)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange>
        implements ExchangeService {

    private final MemberMapper memberMapper;
    private final RewardMapper rewardMapper;

    @Override
    public Boolean exchangeReward(ExchangeReq exchangeReq) {
        Member member = memberMapper.selectById(exchangeReq.getMemberId());
        Reward reward = rewardMapper.selectById(exchangeReq.getRewardId());

        if (member.getPoints() < reward.getPoints()) {
            throw new BusinessException(ResultEnum.POINTS_NOT_ENOUGH);
        }
        member.setPoints(member.getPoints() - reward.getPoints());
        if (memberMapper.updateById(member) <= 0) {
            throw new BusinessException(ResultEnum.POINTS_UPDATE_FAILED);
        }
        Exchange exchange = new Exchange();
        exchange.setMemberId(member.getId());
        exchange.setName(reward.getName());
        exchange.setPoints(reward.getPoints());

        if (baseMapper.insert(exchange) <= 0) {
            throw new BusinessException(ResultEnum.EXCHANGE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeExchange(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.EXCHANGE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeExchanges(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.EXCHANGE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Exchange getExchange(Long id) {
        Exchange exchange = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(exchange)) {
            throw new BusinessException(ResultEnum.EXCHANGE_NOT_EXIST);
        }
        return exchange;
    }

    @Override
    public IPage<Exchange> listExchanges(ListExchangeReq listExchangeReq, Integer current, Integer size) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listExchangeReq.getName()), Member::getName, listExchangeReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listExchangeReq.getContact()), Member::getContact, listExchangeReq.getContact());
        List<Member> members = memberMapper.selectList(lambdaQueryWrapper);
        List<Long> memberIdList = members.stream().map(Member::getId).toList();

        LambdaQueryWrapper<Exchange> exchangeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        exchangeLambdaQueryWrapper.in(!ObjectUtils.isEmpty(memberIdList), Exchange::getMemberId, memberIdList);
        return baseMapper.selectPage(new Page<>(current, size), exchangeLambdaQueryWrapper);
    }
}




