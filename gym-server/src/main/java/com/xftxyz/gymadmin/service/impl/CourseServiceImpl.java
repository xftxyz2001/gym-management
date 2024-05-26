package com.xftxyz.gymadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xftxyz.gymadmin.domain.Coach;
import com.xftxyz.gymadmin.domain.Consume;
import com.xftxyz.gymadmin.domain.Course;
import com.xftxyz.gymadmin.domain.Member;
import com.xftxyz.gymadmin.exception.BusinessException;
import com.xftxyz.gymadmin.mapper.CoachMapper;
import com.xftxyz.gymadmin.mapper.ConsumeMapper;
import com.xftxyz.gymadmin.mapper.CourseMapper;
import com.xftxyz.gymadmin.mapper.MemberMapper;
import com.xftxyz.gymadmin.result.ResultEnum;
import com.xftxyz.gymadmin.service.CourseService;
import com.xftxyz.gymadmin.vo.req.BuyCourseReq;
import com.xftxyz.gymadmin.vo.req.ListCourseReq;
import com.xftxyz.gymadmin.vo.resp.StatisticsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
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
    private final MemberMapper memberMapper;
    private final ConsumeMapper consumeMapper;

    @Override
    public Boolean saveCourse(Course course) {
        // 检查课程是否已存在
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getName, course.getName());
        Course existCourse = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCourse)) {
            throw new BusinessException(ResultEnum.COURSE_EXIST);
        }
        // 检查教练在当前时间是否有课程
        lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getCoachId, course.getCoachId());
        lambdaQueryWrapper.eq(Course::getTimeFrame, course.getTimeFrame());
        existCourse = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCourse)) {
            throw new BusinessException(ResultEnum.COACH_BUSY);
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
        // 检查教练在当前时间是否有课程
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Course::getCoachId, course.getCoachId());
        lambdaQueryWrapper.eq(Course::getTimeFrame, course.getTimeFrame());
        Course existCourse2 = baseMapper.selectOne(lambdaQueryWrapper);
        if (!ObjectUtils.isEmpty(existCourse2) && !existCourse2.getId().equals(course.getId())) {
            throw new BusinessException(ResultEnum.COACH_BUSY);
        }
        if (baseMapper.updateById(course) <= 0) {
            throw new BusinessException(ResultEnum.COURSE_UPDATE_FAILED);
        }
        return true;
    }

    @Override
    public Course getCourse(Long id) {
        Course course = baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(course)) {
            throw new BusinessException(ResultEnum.COURSE_NOT_EXIST);
        }
        return this.courseSetCoach(course);
    }

    @Override
    public IPage<Course> listCourses(ListCourseReq listCourseReq, Integer current, Integer size) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCourseReq.getName()), Course::getName, listCourseReq.getName());
        lambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCourseReq.getTimeFrame()), Course::getTimeFrame, listCourseReq.getTimeFrame());

        if (!ObjectUtils.isEmpty(listCourseReq.getCoachName())) {
            LambdaQueryWrapper<Coach> coachLambdaQueryWrapper = new LambdaQueryWrapper<>();
            coachLambdaQueryWrapper.like(!ObjectUtils.isEmpty(listCourseReq.getCoachName()), Coach::getName, listCourseReq.getCoachName());
            List<Coach> coaches = coachMapper.selectList(coachLambdaQueryWrapper);
            List<Long> coachIdList = coaches.stream().map(Coach::getId).toList();
            lambdaQueryWrapper.in(!ObjectUtils.isEmpty(coachIdList), Course::getCoachId, coachIdList);
        }
        Page<Course> coursePage = baseMapper.selectPage(new Page<>(current, size), lambdaQueryWrapper);
        coursePage.getRecords().forEach(this::courseSetCoach);
        return coursePage;
    }

    @Override
    public StatisticsVO courseStatistics(StatisticsVO statisticsVO) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Date from = statisticsVO.getFrom();
        Date to = statisticsVO.getTo();
        if (ObjectUtils.isEmpty(from) && ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
        } else if (!ObjectUtils.isEmpty(from) && !ObjectUtils.isEmpty(to)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.between(Course::getCreateTime, from, to);
        } else if (!ObjectUtils.isEmpty(from)) {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(Course::getCreateTime, from);
        } else {
            lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.le(Course::getCreateTime, to);
        }
        Long count = baseMapper.selectCount(lambdaQueryWrapper);
        statisticsVO.setCount(BigDecimal.valueOf(count));
        return statisticsVO;
    }

    @Override
    public List<Course> listCoursesByName(String name) {
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Course::getName, name);
        List<Course> courseList = baseMapper.selectList(lambdaQueryWrapper);
        return courseList.stream().map(this::courseSetCoach).toList();
    }

    private Course courseSetCoach(Course course) {
        if (!ObjectUtils.isEmpty(course.getCoachId())) {
            Coach coach = coachMapper.selectById(course.getCoachId());
            if (!ObjectUtils.isEmpty(coach)) {
                course.setCoach(coach);
            }
        }
        return course;
    }

    @Override
    public Boolean buyCourse(BuyCourseReq buyCourseReq) {
        Long memberId = buyCourseReq.getMemberId();
        Long courseId = buyCourseReq.getCourseId();

        Member member = memberMapper.selectById(memberId);
        if (ObjectUtils.isEmpty(member)) {
            throw new BusinessException(ResultEnum.MEMBER_NOT_EXIST);
        }
        Course course = baseMapper.selectById(courseId);
        if (ObjectUtils.isEmpty(course)) {
            throw new BusinessException(ResultEnum.COURSE_NOT_EXIST);
        }

        // 保存消费记录
        Consume consume = new Consume();
        consume.setMemberId(memberId);
        consume.setCtype(Consume.CTYPE_COURSE);
        consume.setCitem(courseId);
        consume.setPayType(buyCourseReq.getPayType());
        consume.setAmount(course.getPrice());
        consume.setStatus(Consume.STATUS_PAID);
        if (consumeMapper.insert(consume) <= 0) {
            throw new BusinessException(ResultEnum.CONSUME_SAVE_FAILED);
        }

        // 更新会员积分
        member.setPoints(member.getPoints() + consume.getAmount().intValue());
        if (memberMapper.updateById(member) <= 0) {
            throw new BusinessException(ResultEnum.MEMBER_UPDATE_FAILED);
        }
        return true;

    }
}




