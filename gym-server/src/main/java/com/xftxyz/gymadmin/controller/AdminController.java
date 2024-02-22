package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.config.GymProperties;
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
    public Boolean changePassword(@RequestHeader(GymProperties.USER_ID) Long userId,
                               @RequestBody @NotNull ChangePasswordReq changePasswordReq) {
        changePasswordReq.setUserId(userId);
        return adminService.changePassword(changePasswordReq);
    }
}
