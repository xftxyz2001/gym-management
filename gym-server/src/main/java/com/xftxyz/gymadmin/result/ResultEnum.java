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
    PHONE_EMPTY(205, "手机号为空"), // 手机号为空

    CARD_SAVE_FAILED(211, "会员卡保存失败"), // 会员卡保存失败
    CARD_REMOVE_FAILED(212, "会员卡删除失败"), // 会员卡删除失败
    CARD_UPDATE_FAILED(213, "会员卡更新失败"), // 会员卡更新失败
    CARD_NOT_EXIST(214, "会员卡不存在"), // 会员卡不存在
    CARD_TYPE_EXIST(215, "卡类型已存在"), // 卡类型已存在
    CARD_TYPE_SAVE_FAILED(216, "卡类型保存失败"), // 卡类型保存失败
    CARD_TYPE_REMOVE_FAILED(217, "卡类型删除失败"), // 卡类型删除失败
    CARD_TYPE_UPDATE_FAILED(218, "卡类型更新失败"), // 卡类型更新失败
    CARD_TYPE_NOT_EXIST(219, "卡类型不存在"), // 卡类型不存在

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

    REWARD_EXIST(250, "兑换物品已存在"), // 兑换物品已存在
    REWARD_SAVE_FAILED(251, "兑换物品保存失败"), // 兑换物品保存失败
    REWARD_REMOVE_FAILED(252, "兑换物品删除失败"), // 兑换物品删除失败
    REWARD_UPDATE_FAILED(253, "兑换物品更新失败"), // 兑换物品更新失败
    REWARD_NOT_EXIST(254, "兑换物品不存在"), // 兑换物品不存在

    EXCHANGE_SAVE_FAILED(261, "兑换记录保存失败"), // 兑换记录保存失败
    EXCHANGE_REMOVE_FAILED(262, "兑换记录删除失败"), // 兑换记录删除失败
    EXCHANGE_UPDATE_FAILED(263, "兑换记录更新失败"), // 兑换记录更新失败
    EXCHANGE_NOT_EXIST(264, "兑换记录不存在"), // 兑换记录不存在

    CONSUME_SAVE_FAILED(271, "消费记录保存失败"), // 消费记录保存失败
    CONSUME_REMOVE_FAILED(272, "消费记录删除失败"), // 消费记录删除失败
    CONSUME_UPDATE_FAILED(273, "消费记录更新失败"), // 消费记录更新失败
    CONSUME_NOT_EXIST(274, "消费记录不存在"), // 消费记录不存在

    REFUND_SAVE_FAILED(281, "退款记录保存失败"), // 退款记录保存失败
    REFUND_REMOVE_FAILED(282, "退款记录删除失败"), // 退款记录删除失败
    REFUND_UPDATE_FAILED(283, "退款记录更新失败"), // 退款记录更新失败
    REFUND_NOT_EXIST(284, "退款记录不存在"), // 退款记录不存在
    CONSUME_STATUS_ERROR(285, "消费状态错误"), // 消费状态错误

    POINTS_NOT_ENOUGH(300, "积分不足"),
    POINTS_UPDATE_FAILED(301, "积分更新失败"),
    EXCHANGE_FAILED(302, "兑换失败"),
    CONSUME_TYPE_ERROR(303, "消费类型错误"),
    REFUND_AMOUNT_ERROR(304, "退款金额错误"),

    // 4参数错误
    PARAM_ERROR(400, "非法参数"),
    PARAM_VALID_ERROR(401, "参数校验失败"),
    ;

    private final Integer code;
    private final String message;

}
