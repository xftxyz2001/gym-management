package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * @author 25810
 * @description 针对表【member(会员信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService {

}




