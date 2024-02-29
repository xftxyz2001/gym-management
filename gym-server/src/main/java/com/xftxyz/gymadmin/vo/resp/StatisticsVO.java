package com.xftxyz.gymadmin.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StatisticsVO {

    // 统计数
    private BigDecimal count;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date from;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date to;
}
