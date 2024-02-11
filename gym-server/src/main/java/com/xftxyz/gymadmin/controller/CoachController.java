package com.xftxyz.gymadmin.controller;

import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/list")
    public List<Coach> list() {
        return coachService.list();
    }
}
