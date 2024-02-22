package com.xftxyz.gymadmin.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {

    // 通用
    FAILED(-1, "失败"), // 失败
    SUCCESS(0, "成功"), // 成功

    LOGIN_ERROR(100, "登录失败，请检查用户名和密码"), // 登录失败
    NOT_LOGIN(101, "未登录"), // 未登录
    PASSWORD_ERROR(102, "密码错误"), // 密码错误

    // 4参数错误
    PARAM_ERROR(400, "非法参数"),
    PARAM_VALID_ERROR(401, "参数校验失败"),
    ;

    private final Integer code;
    private final String message;

}
