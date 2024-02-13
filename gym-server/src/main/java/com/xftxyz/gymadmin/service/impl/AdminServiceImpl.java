package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Admin;
import com.xftxyz.gymadmin.service.AdminService;
import com.xftxyz.gymadmin.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【admin(管理员信息)】的数据库操作Service实现
* @createDate 2024-02-14 07:08:06
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




