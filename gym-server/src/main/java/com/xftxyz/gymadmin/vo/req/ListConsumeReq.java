package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ListConsumeReq {

    /**
     * 会员联系方式
     */
    private String contact;

    /**
     * 消费状态（0已支付、1转入退款、2已完成）
     */
    private Integer status;
}
