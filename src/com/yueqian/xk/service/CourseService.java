package com.yueqian.xk.service;

import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.CourseDetailInfo;
import com.yueqian.xk.beans.CourseInfo;
import com.yueqian.xk.beans.UserCourseInfo;

import java.util.List;

/**
 * 课程service接口
 */
public interface CourseService {
    /**
     * 管理员添加课程
     * @param course
     * @return
     */
    public int addCourse(CourseInfo course);

    /**
     * 管理员查看所有已添加的课程(无分页实现)
     * @return
     */
    public List<CourseInfo> findCourses();

    /**
     * 管理员查看所有已添加的课程（使用pageHelper实现分页）
     * @param pageNum：当前页码
     * @param pageSize：每页显示的数据条数
     * @return
     */
    public PageInfo<CourseInfo> findCourses2(Integer pageNum,Integer pageSize);

    /**
     * 学员查看未选课程
     * @param userId：用户编号
     * @param pageNum：当前页码
     * @param pageSize：每页显示的条数
     * @return
     */
    public PageInfo<CourseDetailInfo> unselectedCourses(int userId,Integer pageNum,Integer pageSize);

    /**
     * 学员选课
     * @param userCourseInfo
     * @return
     */
    public int selectCourse(UserCourseInfo userCourseInfo);

    /**
     * 学员查看已选课程
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CourseDetailInfo> selectedCourses(int userId,Integer pageNum,Integer pageSize);
}
