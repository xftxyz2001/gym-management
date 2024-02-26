package com.xftxyz.gymadmin.vo.req;

import lombok.Data;

@Data
public class ListCourseReq {

    /**
     * 课程名称
     */
    private String name;

    /**
     * 教练名称
     */
    private String coachName;
}
