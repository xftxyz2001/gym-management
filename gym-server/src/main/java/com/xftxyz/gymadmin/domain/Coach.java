package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 教练信息
 *
 * @TableName coach
 */
@TableName(value = "coach")
@Data
public class Coach implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 教练ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 教练姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 教练联系方式
     */
    @TableField(value = "contact")
    private String contact;
    /**
     * 教练技能描述
     */
    @TableField(value = "skill")
    private String skill;
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