package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ListExchangeReq {

    /**
     * 会员姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String contact;
}
