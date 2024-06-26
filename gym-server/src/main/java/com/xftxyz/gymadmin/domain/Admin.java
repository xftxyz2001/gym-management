package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员信息
 *
 * @TableName admin
 */
@TableName(value = "admin")
@Data
public class Admin implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 管理员ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 登录名
     */
    @TableField(value = "login")
    private String login;
    /**
     * 登录密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 描述
     */
    @TableField(value = "detail")
    private String detail;
    /**
     * 角色(0-管理员, ?-教练id)
     */
    @TableField(value = "coach_id")
    private Long coachId;
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