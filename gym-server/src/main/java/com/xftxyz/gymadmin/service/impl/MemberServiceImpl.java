package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.MemberService;
import com.xftxyz.gymadmin.vo.req.ListMemberReq;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 25810
 * @description 针对表【member(会员信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService {

    @Override
    public Boolean saveMember(Member member) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Member::getContact, member.getContact());
        Member existMember = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existMember)) {
            throw new BusinessException(ResultEnum.PHONE_EXIST);
        }
        if (baseMapper.insert(member) <= 0) {
            throw new BusinessException(ResultEnum.MEMBER_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeMember(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.MEMBER_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeMembers(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.MEMBER_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateMember(Member member) {
        Member existMember = baseMapper.selectById(member.getId());
        if (ObjectUtils.isEmpty(existMember)) {
            throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        }
        if (baseMapper.updateById(member) <= 0) {
            throw new BusinessException(ResultEnum.MEMBER_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Member getMember(Long id) {
        Member member = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(member)) {
            throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        }
        return member;
    }

    @Override
    public IPage<Member> listMembers(ListMemberReq listMemberReq, Integer current, Integer size) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listMemberReq.getName()), Member::getName, listMemberReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listMemberReq.getContact()), Member::getContact, listMemberReq.getContact());

        return baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }

    @Override
    public StatisticsVO memberStatistics(StatisticsVO statisticsVO) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Date from = statisticsVO.getFrom();
        Date to = statisticsVO.getTo();
        if (ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
        } else if (!ObjectUtils.isEmpty(from) && !ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.between(Member::getCreateTime, from, to);
        } else if (!ObjectUtils.isEmpty(from)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(Member::getCreateTime, from);
        } else {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.le(Member::getCreateTime, to);
        }
        Long count = baseMapper.selectCount(lambdaQueryWrapper);
        statisticsVO.setCount(BigDecimal.valueOf(count));
        return statisticsVO;
    }
}




