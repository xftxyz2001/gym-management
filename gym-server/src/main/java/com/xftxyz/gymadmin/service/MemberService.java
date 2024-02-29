package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.vo.req.ListMemberReq;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【member(会员信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface MemberService extends IService<Member> {

    Boolean saveMember(Member member);

    Boolean removeMember(Long id);

    Boolean removeMembers(List<Long> idList);

    Boolean updateMember(Member member);

    Member getMember(Long id);

    IPage<Member> listMembers(ListMemberReq listMemberReq, Integer current, Integer size);

    StatisticsVO memberStatistics(StatisticsVO statisticsVO);
}
