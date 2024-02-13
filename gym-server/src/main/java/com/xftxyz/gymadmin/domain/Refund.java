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
 * 退款记录
 * @TableName refund
 */
@TableName(value ="refund")
@Data
public class Refund implements Serializable {
    /**
     * 退款记录ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员ID
     */
    @TableField(value = "member_id")
    private Long memberId;

    /**
     * 关联的消费记录ID
     */
    @TableField(value = "consume_id")
    private Long consumeId;

    /**
     * 退款金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

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