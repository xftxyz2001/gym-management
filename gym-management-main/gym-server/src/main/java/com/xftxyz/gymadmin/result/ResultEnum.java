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

    PHONE_EXIST(200, "手机号已存在"), // 手机号已存在
    MEMBER_SAVE_FAILED(201, "会员保存失败"), // 会员保存失败
    MEMBER_REMOVE_FAILED(202, "会员删除失败"), // 会员删除失败
    MEMBER_UPDATE_FAILED(203, "会员更新失败"), // 会员更新失败
    MEMBER_NOT_EXIST(204, "会员不存在"), // 会员不存在

    CARD_SAVE_FAILED(211, "会员卡保存失败"), // 会员卡保存失败
    CARD_REMOVE_FAILED(212, "会员卡删除失败"), // 会员卡删除失败
    CARD_UPDATE_FAILED(213, "会员卡更新失败"), // 会员卡更新失败
    CARD_NOT_EXIST(214, "会员卡不存在"), // 会员卡不存在


    // 4参数错误
    PARAM_ERROR(400, "非法参数"),
    PARAM_VALID_ERROR(401, "参数校验失败"),
    ;

    private final Integer code;
    private final String message;

}
