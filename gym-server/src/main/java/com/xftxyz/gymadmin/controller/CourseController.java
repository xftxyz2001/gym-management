package com.xftxyz.gymadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xftxyz.gymadmin.config.GymProperties;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.domain.Course;
import com.xftxyz.gymadmin.domain.Project;
import com.xftxyz.gymadmin.service.CoachService;
import com.xftxyz.gymadmin.service.CourseService;
import com.xftxyz.gymadmin.service.ProjectService;
import com.xftxyz.gymadmin.vo.req.BuyCourseReq;
import com.xftxyz.gymadmin.vo.req.ListCoachReq;
import com.xftxyz.gymadmin.vo.req.ListCourseReq;
import com.xftxyz.gymadmin.vo.req.ListProjectReq;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final ProjectService projectService;
    private final CourseService courseService;
    private final CoachService coachService;

    // 新增项目
    @PostMapping("/project")
    public Boolean saveProject(@RequestBody @NotNull Project project) {
        return projectService.saveProject(project);
    }

    // 删除项目
    @DeleteMapping("/project/{id}")
    public Boolean removeProject(@PathVariable("id") @Min(1) Long id) {
        return projectService.removeProject(id);
    }

    // 批量删除项目
    @DeleteMapping("/projects")
    public Boolean removeProjects(@RequestBody @NotEmpty List<Long> idList) {
        return projectService.removeProjects(idList);
    }

    // 更新项目
    @PutMapping("/project")
    public Boolean updateProject(@RequestBody @NotNull Project project) {
        return projectService.updateProject(project);
    }

    // 查询项目
    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable("id") @Min(1) Long id) {
        return projectService.getProject(id);
    }

    // 条件查询项目
    @PostMapping("/projects")
    public IPage<Project> listProjects(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                       @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                       @RequestBody ListProjectReq listProjectReq) {
        return projectService.listProjects(listProjectReq, current, size);
    }


    // 新增课程
    @PostMapping("/course")
    public Boolean saveCourse(@RequestBody @NotNull Course course) {
        return courseService.saveCourse(course);
    }

    // 删除课程
    @DeleteMapping("/course/{id}")
    public Boolean removeCourse(@PathVariable("id") @Min(1) Long id) {
        return courseService.removeCourse(id);
    }

    // 批量删除课程
    @DeleteMapping("/courses")
    public Boolean removeCourses(@RequestBody @NotEmpty List<Long> idList) {
        return courseService.removeCourses(idList);
    }

    // 更新课程
    @PutMapping("/course")
    public Boolean updateCourse(@RequestBody @NotNull Course course) {
        return courseService.updateCourse(course);
    }

    // 查询课程
    @GetMapping("/course/{id}")
    public Course getCourse(@PathVariable("id") @Min(1) Long id) {
        return courseService.getCourse(id);
    }

    // 条件查询课程
    @PostMapping("/courses")
    public IPage<Course> listCourses(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                     @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                     @RequestBody ListCourseReq listCourseReq,
                                     @RequestAttribute(GymProperties.USER_ID) @NotNull Long userId) {
        return courseService.listCourses(listCourseReq, current, size, userId);
    }

    // 获取课程列表
    @GetMapping("/courses/list")
    public List<Course> listCoursesByName(@RequestParam(value = "name") @NotNull String name) {
        return courseService.listCoursesByName(name);
    }

    // 购买课程
    @PostMapping("/buy")
    public Boolean buyCourse(@RequestBody @NotNull BuyCourseReq buyCourseReq) {
        return courseService.buyCourse(buyCourseReq);
    }


    // 新增教练
    @PostMapping("/coach")
    public Boolean saveCoach(@RequestBody @NotNull Coach coach) {
        return coachService.saveCoach(coach);
    }

    // 删除教练
    @DeleteMapping("/coach/{id}")
    public Boolean removeCoach(@PathVariable("id") @Min(1) Long id) {
        return coachService.removeCoach(id);
    }

    // 批量删除教练
    @DeleteMapping("/coaches")
    public Boolean removeCoaches(@RequestBody @NotEmpty List<Long> idList) {
        return coachService.removeCoaches(idList);
    }

    // 更新教练
    @PutMapping("/coach")
    public Boolean updateCoach(@RequestBody @NotNull Coach coach) {
        return coachService.updateCoach(coach);
    }

    // 查询教练
    @GetMapping("/coach/{id}")
    public Coach getCoach(@PathVariable("id") @Min(1) Long id) {
        return coachService.getCoach(id);
    }

    // 获取教练列表
    @GetMapping("/coach/list")
    public List<Coach> listCoachsByName(@RequestParam(value = "name") @NotNull String name) {
        return coachService.listCoachsByName(name);
    }

    // 条件查询教练
    @PostMapping("/coaches")
    public IPage<Coach> listCoaches(@RequestParam(value = "current", defaultValue = "1") @Min(1) Integer current,
                                    @RequestParam(value = "size", defaultValue = "20") @Min(1) Integer size,
                                    @RequestBody ListCoachReq listCoachReq) {
        return coachService.listCoaches(listCoachReq, current, size);
    }
}
