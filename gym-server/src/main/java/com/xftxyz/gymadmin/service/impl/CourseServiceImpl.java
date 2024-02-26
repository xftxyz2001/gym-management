package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.domain.Course;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.CoachMapper;
import com.xftxyz.gymadmin.mapper.CourseMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CourseService;
import com.xftxyz.gymadmin.vo.req.ListCourseReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 25810
 * @description 针对表【course(课程信息)】的数据库操作Service实现
 * @createDate 2024-02-14 07:08:06
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    private final CoachMapper coachMapper;

    @Override
    public Boolean saveCourse(Course course) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getName, course.getName());
        Course existCourse = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCourse)) {
            throw new BusinessException(ResultEnum.COURSE_EXIST);
        }
        if (baseMapper.insert(course) <= 0) {
            throw new BusinessException(ResultEnum.COURSE_SAVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCourse(Long id) {
        if (baseMapper.deleteById(id) <= 0) {
            throw new BusinessException(ResultEnum.COURSE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean removeCourses(List<Long> idList) {
        if (baseMapper.deleteBatchIds(idList) < idList.size()) {
            throw new BusinessException(ResultEnum.COURSE_REMOVE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean updateCourse(Course course) {
        Course existCourse = baseMapper.selectById(course.getId());
        if (ObjectUtils.isEmpty(existCourse)) {
            throw new BusinessException(ResultEnum.COURSE_NOT_EXIST);
        }
        if (baseMapper.updateById(course) <= 0) {
            throw new BusinessException(ResultEnum.COURSE_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Course getCourse(Long id) {
        Course course = baseMapper.selectById(id);
        if (course == null) {
            throw new BusinessException(ResultEnum.COURSE_NOT_EXIST);
        }
        return course;
    }

    @Override
    public IPage<Course> listCourses(ListCourseReq listCourseReq, Integer current, Integer size) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCourseReq.getName()), Course::getName, listCourseReq.getName());

        if (!ObjectUtils.isEmpty(listCourseReq.getCoachName())) {
            LambdaQueryWrapper<Coach> coachLambdaQueryWrapper = new LambdaQueryWrapper<>();
            coachLambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCourseReq.getCoachName()), Coach::getName, listCourseReq.getCoachName());
            List<Coach> coaches = coachMapper.selectList(coachLambdaQueryWrapper);
            List<Long> coachIdList = coaches.stream().map(Coach::getId).toList();
            lambdaQueryWrapper.in(!ObjectUtils.isEmpty(coachIdList), Course::getCoachId, coachIdList);
        }
        return baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
    }
}




