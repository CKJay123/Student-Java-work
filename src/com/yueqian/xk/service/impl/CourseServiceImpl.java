package com.yueqian.xk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.CourseDetailInfo;
import com.yueqian.xk.beans.CourseInfo;
import com.yueqian.xk.beans.UserCourseInfo;
import com.yueqian.xk.mapper.CourseMapper;
import com.yueqian.xk.mapper.UserCourseMapper;
import com.yueqian.xk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程service实现类
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserCourseMapper userCourseMapper;
    /**
     * 管理员添加课程
     * @param course
     * @return
     */
    @Override
    public int addCourse(CourseInfo course) {
        return courseMapper.addCourse(course);
    }

    /**
     * 管理员查看所有已添加的课程
     * @return
     */
    @Override
    public List<CourseInfo> findCourses() {
        return courseMapper.findCourses();
    }

    /**
     * 管理员查看所有已添加的课程（使用pageHelper实现分页）
     *
     * @param pageNum  ：当前页码
     * @param pageSize ：每页显示的数据条数
     * @return
     */
    @Override
    public PageInfo<CourseInfo> findCourses2(Integer pageNum, Integer pageSize) {
        //设置当前页码和每页显示的条数
        PageHelper.startPage(pageNum,pageSize);
        //正常的数据查询，不带分页
        List<CourseInfo> list = courseMapper.findCourses();
        //通过pageHelper对查询出来的数据进行分页封装
        PageInfo<CourseInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 学员查看未选课程
     *
     * @param userId   ：用户编号
     * @param pageNum  ：当前页码
     * @param pageSize ：每页显示的条数
     * @return
     */
    @Override
    public PageInfo<CourseDetailInfo> unselectedCourses(int userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseDetailInfo> list = userCourseMapper.unselectedCourses(userId);
        PageInfo<CourseDetailInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 学员选课
     *
     * @param userCourseInfo
     * @return
     */
    @Override
    public int selectCourse(UserCourseInfo userCourseInfo) {
        //更新选课人数
        courseMapper.updateCourse(userCourseInfo.getCourseId());
        return userCourseMapper.addUserCourse(userCourseInfo);
    }

    /**
     * 学员查看已选课程
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CourseDetailInfo> selectedCourses(int userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseDetailInfo> list = userCourseMapper.selectedCourses(userId);
        PageInfo<CourseDetailInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
