package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ExchangeReq {

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 兑换物品ID
     */
    private Long rewardId;
}
