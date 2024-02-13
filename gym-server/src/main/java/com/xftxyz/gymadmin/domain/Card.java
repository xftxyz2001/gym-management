package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员卡信息
 * @TableName card
 */
@TableName(value ="card")
@Data
public class Card implements Serializable {
    /**
     * 卡ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 所属会员ID
     */
    @TableField(value = "member_id")
    private Long memberId;

    /**
     * 卡类型ID
     */
    @TableField(value = "card_type")
    private Long cardType;

    /**
     * 有效期
     */
    @TableField(value = "valid_time")
    private Date validTime;

    /**
     * 总次数/金额（-1无效）
     */
    @TableField(value = "total")
    private Integer total;

    /**
     * 剩余次数/金额
     */
    @TableField(value = "remain")
    private Integer remain;

    /**
     * 状态（0正常、1过期、-1注销）
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