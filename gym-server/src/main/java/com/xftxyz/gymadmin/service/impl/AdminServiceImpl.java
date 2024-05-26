package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Admin;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.helper.JwtHelper;
import com.xftxyz.gymadmin.mapper.AdminMapper;
import com.xftxyz.gymadmin.mapper.CoachMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.AdminService;
import com.xftxyz.gymadmin.vo.req.ChangePasswordReq;
import com.xftxyz.gymadmin.vo.req.LoginReq;
import com.xftxyz.gymadmin.vo.resp.LoginResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author 25810
 * @description 针对表【admin(管理员信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

    private final CoachMapper coachMapper;

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

    @Override
    public Admin getCoachLoginInfo(Long coachId) {
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getCoachId, coachId);
        return baseMapper.selectOne(adminLambdaQueryWrapper);
    }

    @Override
    public Boolean setCoachLoginInfo(Admin admin) {
        // 检查教练是否存在
        LambdaQueryWrapper<Coach> coachLambdaQueryWrapper = new LambdaQueryWrapper<>();
        coachLambdaQueryWrapper.eq(Coach::getId, admin.getCoachId());
        Coach coach = coachMapper.selectOne(coachLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(coach)) {
            throw new BusinessException(ResultEnum.COACH_NOT_EXIST);
        }
        // 检查登录名是否为空
        if (!StringUtils.hasLength(admin.getLogin())) {
            throw new BusinessException(ResultEnum.LOGIN_EMPTY);
        }
        // 检查登录名是否重复
        LambdaQueryWrapper<Admin> loginLambdaQueryWrapper = new LambdaQueryWrapper<>();
        loginLambdaQueryWrapper.eq(Admin::getLogin, admin.getLogin());
        Admin oldLogin = baseMapper.selectOne(loginLambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(oldLogin) && !oldLogin.getCoachId().equals(admin.getCoachId())) {
            throw new BusinessException(ResultEnum.LOGIN_EXIST);
        }

        // 设置教练的登录展示昵称
        admin.setName(coach.getName());

        // 保存或更新
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getCoachId, admin.getCoachId());
        Admin oldAdmin = baseMapper.selectOne(adminLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(oldAdmin)) {
            return baseMapper.insert(admin) > 0;
        } else {
            admin.setId(oldAdmin.getId());
            return baseMapper.updateById(admin) > 0;
        }
    }
}




