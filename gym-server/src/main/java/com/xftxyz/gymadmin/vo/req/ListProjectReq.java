package com.xftxyz.gymadmin.vo.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ListProjectReq {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;
}
