package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.domain.Refund;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.ConsumeMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.mapper.RefundMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.RefundService;
import com.xftxyz.gymadmin.vo.req.ListRefundReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【refund(退款记录)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:07
 */
@Service
@RequiredArgsConstructor
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund>
        implements RefundService {

    private final ConsumeMapper consumeMapper;
    private final MemberMapper memberMapper;

    @Override
    public Boolean saveRefund(Refund refund) {
        Consume consume = consumeMapper.selectById(refund.getConsumeId());
        if (ObjectUtils.isEmpty(consume)) {
            throw new BusinessException(ResultEnum.CONSUME_NOT_EXIST);
        }
        if (consume.getAmount().compareTo(refund.getAmount()) < 0) {
            throw new BusinessException(ResultEnum.REFUND_AMOUNT_ERROR);
        }
        if (baseMapper.insert(refund) <= 0) {
            throw new BusinessException(ResultEnum.REFUND_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeRefund(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.REFUND_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeRefunds(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.REFUND_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateRefund(Refund refund) {
        Refund existRefund = baseMapper.selectById(refund.getId());
        if (ObjectUtils.isEmpty(existRefund)) {
            throw new BusinessException(ResultEnum.REFUND_NOT_EXIST);
        }
        if (baseMapper.updateById(refund) <= 0) {
            throw new BusinessException(ResultEnum.REFUND_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Refund getRefund(Long id) {
        Refund refund = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(refund)) {
            throw new BusinessException(ResultEnum.REFUND_NOT_EXIST);
        }
        return refund;
    }

    @Override
    public IPage<Refund> listRefunds(ListRefundReq listRefundReq, Integer current, Integer size) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listRefundReq.getContact()), Member::getContact, listRefundReq.getContact());
        List<Member> members = memberMapper.selectList(lambdaQueryWrapper);
        List<Long> memberIdList = members.stream().map(Member::getId).toList();

        LambdaQueryWrapper<Refund> consumeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        consumeLambdaQueryWrapper.in(!ObjectUtils.isEmpty(memberIdList), Refund::getMemberId, memberIdList);
        return baseMapper.selectPage(new Page<>(current, size), consumeLambdaQueryWrapper);
    }
}




