package com.yueqian.xk.beans;

/**
 * 课程明细实体（数据库没有对应的内容，通过查询组装）
 */
public class CourseDetailInfo extends CourseInfo{
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
