package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Admin;
import com.xftxyz.gymadmin.vo.req.ChangePasswordReq;
import com.xftxyz.gymadmin.vo.req.LoginReq;
import com.xftxyz.gymadmin.vo.resp.LoginResp;

/**
 * @author 25810
 * @description 针对表【admin(管理员信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface AdminService extends IService<Admin> {

    LoginResp login(LoginReq loginReq);

    Boolean changePassword(ChangePasswordReq changePasswordReq);

    Admin getCoachLoginInfo(Long coachId);

    Boolean setCoachLoginInfo(Admin admin);
}
