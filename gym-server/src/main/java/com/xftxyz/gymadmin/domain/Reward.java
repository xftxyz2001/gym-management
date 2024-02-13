package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 奖励信息
 * @TableName reward
 */
@TableName(value ="reward")
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