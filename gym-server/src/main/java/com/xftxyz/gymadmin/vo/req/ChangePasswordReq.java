package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ChangePasswordReq {

    private Long userId;
    private String oldPassword;
    private String newPassword;
}
