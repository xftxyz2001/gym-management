package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ListCoachReq {

    /**
     * 教练姓名
     */
    private String name;

    /**
     * 教练技能描述
     */
    private String skill;
}
