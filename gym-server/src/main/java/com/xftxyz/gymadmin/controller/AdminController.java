package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.service.AdminService;
import com.xftxyz.gymadmin.vo.req.LoginReq;
import com.xftxyz.gymadmin.vo.resp.LoginResp;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
