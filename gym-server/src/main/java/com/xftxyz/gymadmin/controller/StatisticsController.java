package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.service.CoachService;
import com.xftxyz.gymadmin.service.ConsumeService;
import com.xftxyz.gymadmin.service.CourseService;
import com.xftxyz.gymadmin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final MemberService memberService;
    private final CoachService coachService;
    private final CourseService courseService;
    private final ConsumeService consumeService;
}
