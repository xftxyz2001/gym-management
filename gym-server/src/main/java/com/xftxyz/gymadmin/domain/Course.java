package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程信息
 *
 * @TableName course
 */
@TableName(value = "course")
@Data
public class Course implements Serializable {
    /**
     * 课程ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 课程名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 教练ID
     */
    @TableField(value = "coach_id")
    private Long coachId;

    /**
     * 课程时长
     */
    @TableField(value = "duration")
    private Date duration;

    /**
     * 课程价格
     */
    @TableField(value = "price")
    private BigDecimal price;

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