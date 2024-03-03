package com.xftxyz.gymadmin.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MemberLoginResp {

    private String cardType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validTime;

    private Integer remain;

    private Integer status;



    private Long memberId;

    private String name;

    private Integer gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private BigDecimal height;

    private BigDecimal weight;

    private String bodyType;

    private String contact;

    private String address;

    private Integer points;
}
