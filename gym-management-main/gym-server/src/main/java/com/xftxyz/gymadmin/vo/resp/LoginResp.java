package com.xftxyz.gymadmin.vo.resp;

import com.xftxyz.gymadmin.domain.Admin;
import lombok.Data;

@Data
public class LoginResp {

    private String token;
    private Admin admin;
}
