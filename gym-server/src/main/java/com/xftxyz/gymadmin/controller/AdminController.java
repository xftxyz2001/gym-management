package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.config.GymProperties;
import com.xftxyz.gymadmin.domain.Admin;
import com.xftxyz.gymadmin.service.AdminService;
import com.xftxyz.gymadmin.vo.req.ChangePasswordReq;
import com.xftxyz.gymadmin.vo.req.LoginReq;
import com.xftxyz.gymadmin.vo.resp.LoginResp;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    // 登录
    @PostMapping("/login")
    public LoginResp login(@RequestBody @NotNull LoginReq loginReq) {
        return adminService.login(loginReq);
    }

    // 修改密码
    @PostMapping("/password/change")
    public Boolean changePassword(@RequestAttribute(GymProperties.USER_ID) Long userId,
                                  @RequestBody @NotNull ChangePasswordReq changePasswordReq) {
        changePasswordReq.setUserId(userId);
        return adminService.changePassword(changePasswordReq);
    }

    // 查看教练的登录信息
    @GetMapping("/coach/{coachId}")
    public Admin getCoachLoginInfo(@PathVariable Long coachId) {
        return adminService.getCoachLoginInfo(coachId);
    }

    // 设置教练的登录信息
    @PostMapping("/coach")
    public Boolean setCoachLoginInfo(@RequestBody Admin admin) {
        return adminService.setCoachLoginInfo(admin);
    }
}
