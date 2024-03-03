package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class BuyCourseReq {

    private Long memberId;
    private Long courseId;
    private Integer payType;
}
