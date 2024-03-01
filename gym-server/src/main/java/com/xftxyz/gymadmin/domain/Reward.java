package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖励信息
 *
 * @TableName reward
 */
@TableName(value = "reward")
@Data
public class Reward implements Serializable {
    /**
     * 奖励ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 兑换物品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 所需积分
     */
    @TableField(value = "points")
    private Integer points;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}