package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 消费记录
 * @TableName consume
 */
@TableName(value ="consume")
@Data
public class Consume implements Serializable {
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
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否已删除(0-未删除, 1-已删除)
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}