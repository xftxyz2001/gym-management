package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Admin;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.helper.JwtHelper;
import com.xftxyz.gymadmin.mapper.AdminMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.AdminService;
import com.xftxyz.gymadmin.vo.req.ChangePasswordReq;
import com.xftxyz.gymadmin.vo.req.LoginReq;
import com.xftxyz.gymadmin.vo.resp.LoginResp;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author 25810
 * @description 针对表【admin(管理员信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

    @Override
    public LoginResp login(LoginReq loginReq) {
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getLogin, loginReq.getLogin());
        adminLambdaQueryWrapper.eq(Admin::getPassword, loginReq.getPassword());
        Admin admin = baseMapper.selectOne(adminLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(admin)) {
            throw new BusinessException(ResultEnum.LOGIN_ERROR);
        }
        LoginResp loginResp = new LoginResp();
        loginResp.setAdmin(admin);
        loginResp.setToken(JwtHelper.generateToken(admin.getId()));
        return loginResp;
    }

    @Override
    public Boolean changePassword(ChangePasswordReq changePasswordReq) {
        Admin admin = baseMapper.selectById(changePasswordReq.getUserId());
        if (ObjectUtils.isEmpty(admin) || !admin.getPassword().equals(changePasswordReq.getOldPassword())) {
            throw new BusinessException(ResultEnum.PASSWORD_ERROR);
        }
        admin.setPassword(changePasswordReq.getNewPassword());
        return baseMapper.updateById(admin) > 0;
    }
}




