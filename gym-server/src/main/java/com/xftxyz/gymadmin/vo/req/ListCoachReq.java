package com.xftxyz.gymadmin.vo.req;

import com.baomidou.mybatisplus.annotation.TableField;
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
