package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class RegisterReq {

    private Long memberId;
    private Long cardTypeId;
    private Integer payType;
}
