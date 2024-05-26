package com.xftxyz.gymadmin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员卡信息
 *
 * @TableName card
 */
@TableName(value = "card")
@Data
public class Card implements Serializable {

    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_EXPIRED = 1;
    public static final int STATUS_CANCELLED = -1;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    private Member member;

    @TableField(exist = false)
    private CardType cardTypeEntity;
}