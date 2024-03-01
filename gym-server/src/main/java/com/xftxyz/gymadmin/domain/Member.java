package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息
 *
 * @TableName member
 */
@TableName(value = "member")
@Data
public class Member implements Serializable {
    /**
     * 会员ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 身高
     */
    @TableField(value = "height")
    private BigDecimal height;

    /**
     * 体重
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 体型
     */
    @TableField(value = "body_type")
    private String bodyType;

    /**
     * 联系方式
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 积分
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