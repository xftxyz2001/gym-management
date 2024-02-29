package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.service.CardService;
import com.xftxyz.gymadmin.service.ConsumeService;
import com.xftxyz.gymadmin.service.CourseService;
import com.xftxyz.gymadmin.service.MemberService;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final MemberService memberService;
    private final CardService cardService;
    private final CourseService courseService;
    private final ConsumeService consumeService;

    // 注册会员数
    @PostMapping("/member")
    public StatisticsVO memberStatistics(StatisticsVO statisticsVO) {
        return memberService.memberStatistics(statisticsVO);
    }

    // 累计办卡数
    @PostMapping("/card")
    public StatisticsVO cardStatistics(StatisticsVO statisticsVO) {
        return cardService.cardStatistics(statisticsVO);
    }

    // 训练课程数
    @PostMapping("/course")
    public StatisticsVO courseStatistics(StatisticsVO statisticsVO) {
        return courseService.courseStatistics(statisticsVO);
    }

    // 累计收入
    @PostMapping("/income")
    public StatisticsVO incomeStatistics(StatisticsVO statisticsVO) {
        return consumeService.incomeStatistics(statisticsVO);
    }
}
