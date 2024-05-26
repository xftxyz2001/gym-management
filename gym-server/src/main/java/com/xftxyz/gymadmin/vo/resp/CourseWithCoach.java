package com.xftxyz.gymadmin.vo.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseWithCoach {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 教练ID
     */
    private Long coachId;

    /**
     * 课程时长
     */
    private String duration;

    /**
     * 课程价格
     */
    private BigDecimal price;

    /**
     * 教练姓名
     */
    private String coach;

    /**
     * 教练联系方式
     */
    private String contact;

    /**
     * 教练技能描述
     */
    private String skill;
}
