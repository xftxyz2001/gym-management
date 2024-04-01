package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundReq {

    // 退款的消费记录id
    private Long consumeId;

    // 退款金额
    private BigDecimal amount;
}
