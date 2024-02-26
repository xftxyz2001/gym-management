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

    PROJECT_EXIST(220, "项目已存在"), // 项目已存在
    PROJECT_SAVE_FAILED(221, "项目保存失败"), // 项目保存失败
    PROJECT_REMOVE_FAILED(222, "项目删除失败"), // 项目删除失败
    PROJECT_UPDATE_FAILED(223, "项目更新失败"), // 项目更新失败
    PROJECT_NOT_EXIST(224, "项目不存在"), // 项目不存在

    COURSE_EXIST(230, "课程已存在"), // 课程已存在
    COURSE_SAVE_FAILED(231, "课程保存失败"), // 课程保存失败
    COURSE_REMOVE_FAILED(232, "课程删除失败"), // 课程删除失败
    COURSE_UPDATE_FAILED(233, "课程更新失败"), // 课程更新失败
    COURSE_NOT_EXIST(234, "课程不存在"), // 课程不存在

    COACH_EXIST(240, "教练已存在"), // 教练已存在
    COACH_SAVE_FAILED(241, "教练保存失败"), // 教练保存失败
    COACH_REMOVE_FAILED(242, "教练删除失败"), // 教练删除失败
    COACH_UPDATE_FAILED(243, "教练更新失败"), // 教练更新失败
    COACH_NOT_EXIST(244, "教练不存在"), // 教练不存在



    // 4参数错误
    PARAM_ERROR(400, "非法参数"),
    PARAM_VALID_ERROR(401, "参数校验失败"),
    ;

    private final Integer code;
    private final String message;

}
