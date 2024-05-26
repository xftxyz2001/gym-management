package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费记录
 *
 * @TableName consume
 */
@TableName(value = "consume")
@Data
public class Consume implements Serializable {

    public static final int CTYPE_CARD = 0;
    public static final int CTYPE_COURSE = 1;

    public static final int PAY_TYPE_CASH = 0;
    public static final int PAY_TYPE_CARD = 1;
    public static final int PAY_TYPE_ALIPAY = 2;
    public static final int PAY_TYPE_WECHAT = 3;

    public static final int STATUS_PAID = 0;
    public static final int STATUS_REFUND = 1;
    public static final int STATUS_COMPLETED = 2;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 消费记录ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 会员ID
     */
    @TableField(value = "member_id")
    private Long memberId;
    /**
     * 消费类型（0办卡、1课程）
     */
    @TableField(value = "ctype")
    private Integer ctype;
    /**
     * 消费项目ID
     */
    @TableField(value = "citem")
    private Long citem;
    /**
     * 支付方式（0现金、1刷卡、2支付宝、3微信）
     */
    @TableField(value = "pay_type")
    private Integer payType;
    /**
     * 消费金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;
    /**
     * 消费状态（0已支付、1转入退款、2已完成）
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 是否已删除(0-未删除, 1-已删除)
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}