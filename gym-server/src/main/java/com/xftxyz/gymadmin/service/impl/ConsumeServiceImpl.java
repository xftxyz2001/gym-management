package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.ConsumeMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.ConsumeService;
import com.xftxyz.gymadmin.vo.req.ListConsumeReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【consume(消费记录)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
@RequiredArgsConstructor
public class ConsumeServiceImpl extends ServiceImpl<ConsumeMapper, Consume>
        implements ConsumeService {

    private final MemberMapper memberMapper;

    @Override
    public Boolean saveConsume(Consume consume) {
        // Integer ctype = consume.getCtype();
        // if (ObjectUtils.isEmpty(ctype)) {
        //     throw new BusinessException(ResultEnum.CONSUME_TYPE_ERROR);
        // }
        // if (ctype < 0 || ctype > 3) {
        //     throw new BusinessException(ResultEnum.CONSUME_TYPE_ERROR);
        // }
        // if (ctype == 0) { // 办卡
        //
        // }else if (ctype == 1) { // 购买课程
        // }
        if (baseMapper.insert(consume) <= 0) {
            throw new BusinessException(ResultEnum.CONSUME_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeConsume(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.CONSUME_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeConsumes(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.CONSUME_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateConsume(Consume consume) {
        Consume existConsume = baseMapper.selectById(consume.getId());
        if (ObjectUtils.isEmpty(existConsume)) {
            throw new BusinessException(ResultEnum.CONSUME_NOT_EXIST);
        }
        if (baseMapper.updateById(consume) <= 0) {
            throw new BusinessException(ResultEnum.CONSUME_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Consume getConsume(Long id) {
        Consume consume = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(consume)) {
            throw new BusinessException(ResultEnum.CONSUME_NOT_EXIST);
        }
        return consume;
    }

    @Override
    public IPage<Consume> listConsumes(ListConsumeReq listConsumeReq, Integer current, Integer size) {
        LambdaQueryWrapper<Consume> consumeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        consumeLambdaQueryWrapper.eq(!ObjectUtils.isEmpty(listConsumeReq.getStatus()), Consume::getStatus, listConsumeReq.getStatus());

        if (!ObjectUtils.isEmpty(listConsumeReq.getContact())) {
            LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listConsumeReq.getContact()), Member::getContact, listConsumeReq.getContact());
            List<Member> members = memberMapper.selectList(lambdaQueryWrapper);
            List<Long> memberIdList = members.stream().map(Member::getId).toList();
            consumeLambdaQueryWrapper.in(!ObjectUtils.isEmpty(memberIdList), Consume::getMemberId, memberIdList);
        }
        return baseMapper.selectPage(new Page<>(current, size), consumeLambdaQueryWrapper);
    }
}




