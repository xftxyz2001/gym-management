package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡类型信息
 *
 * @TableName card_type
 */
@TableName(value = "card_type")
@Data
public class CardType implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 卡类型ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 卡类型名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 有效期（天）
     */
    @TableField(value = "valid_time")
    private Integer validTime;
    /**
     * 次数（-1为无限）
     */
    @TableField(value = "count")
    private Integer count;
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