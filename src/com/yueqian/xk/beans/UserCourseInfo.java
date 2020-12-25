package com.yueqian.xk.beans;

/**
 * 用户课程实体类（跟user_course对应）
 */
public class UserCourseInfo {
    private Integer userId;
    private Integer courseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
