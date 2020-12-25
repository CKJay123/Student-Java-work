package com.yueqian.xk.mapper;

import com.yueqian.xk.beans.CourseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 课程dao
 */
@Mapper
public interface CourseMapper {
    /**
     * 添加课程
     * @param course
     * @return
     */
    @Insert(value = "INSERT INTO course(coursename,teacher,number) VALUES(#{coursename},#{teacher},0)")
    int addCourse(CourseInfo course);

    /**
     * 查看所有管理员已添加的课程
      * @return
     */
    @Select(value="select * from course")
    List<CourseInfo> findCourses();

    /**
     * 根据课程编号，修改选课数量（获取原本的选课数量+1）
     * @param courseId
     * @return
     */
    @Update(value="update course set number = number+1 where course_id=#{courseId}")
    int updateCourse(int courseId);
}
