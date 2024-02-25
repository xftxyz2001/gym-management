package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ListCardReq {

    /**
     * 卡ID
     */
    private Long id;

    /**
     * 所属会员ID
     */
    private Long memberId;
}
