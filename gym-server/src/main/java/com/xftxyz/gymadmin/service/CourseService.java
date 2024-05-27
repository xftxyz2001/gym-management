package com.xftxyz.gymadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xftxyz.gymadmin.domain.Course;
import com.xftxyz.gymadmin.vo.req.BuyCourseReq;
import com.xftxyz.gymadmin.vo.req.ListCourseReq;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【course(课程信息)】的数据库操作Service
 * @createDate 2024-02-14 07:08:06
 */
public interface CourseService extends IService<Course> {

    Boolean saveCourse(Course course);

    Boolean removeCourse(Long id);

    Boolean removeCourses(List<Long> idList);

    Boolean updateCourse(Course course);

    Course getCourse(Long id);

    IPage<Course> listCourses(ListCourseReq listCourseReq, Integer current, Integer size, Long userId);

    StatisticsVO courseStatistics(StatisticsVO statisticsVO);

    List<Course> listCoursesByName(String name);

    Boolean buyCourse(BuyCourseReq buyCourseReq);

    IPage<Course> listCoursesByCoach(ListCourseReq listCourseReq, Integer current, Integer size, Long coachId);
}
