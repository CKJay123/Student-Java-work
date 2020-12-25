package com.yueqian.xk.controller;

import com.github.pagehelper.PageInfo;
import com.yueqian.xk.beans.*;
import com.yueqian.xk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 对应course的控制器
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 添加课程
     * @param courseInfo：接收前台的json，直接转换为对象
     * @param session
     * @return
     */
    @PostMapping("/ajax/input")
    @ResponseBody
    public AjaxResponseInfo inputData(@RequestBody CourseInfo courseInfo, HttpSession session){//alt+enter:导包
          AjaxResponseInfo responseInfo = new AjaxResponseInfo();
          //从session中取得当前登录系统的用户信息
        UserInfo user =(UserInfo) session.getAttribute("loginedUser");
        if(user==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("您还没有登录！");
        }else{
            if(user.getRole()!=0){
                responseInfo.setCode(-2);
                responseInfo.setMsg("您不是管理员，没有该权限！");
            }else{
                //将数据保存
                int row = courseService.addCourse(courseInfo);
                if(row>0){
                    responseInfo.setMsg("课程添加成功！");
                }else{
                    responseInfo.setCode(-1);
                    responseInfo.setMsg("课程添加失败！");
                }
            }

        }
        return responseInfo;
    }

    /**
     * 管理员查询已添加的课程（无分页）
     * @return
     */
    @GetMapping("/ajax/findCourses")
    @ResponseBody
    public AjaxResponseInfo findCourses(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<CourseInfo> list = courseService.findCourses();
        if(list==null || list.size()==0){
            responseInfo.setCode(-1);
            responseInfo.setMsg("没有获取到数据！");
        }else{
            responseInfo.setData(list);
        }
        return responseInfo;
    }

    /**
     * 管理员查询已添加的课程（有分页）
     * @param pageNum
     * @return
     */
    @GetMapping("/ajax/findCourses2")
    @ResponseBody
    public AjaxResponseInfo findCourses2(@RequestParam(defaultValue = "1",required = true,value = "pageNum")Integer pageNum){//?pageNum=1
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        PageInfo<CourseInfo> list = courseService.findCourses2(pageNum,5);//每页显示5条
        if(list==null || list.getSize()==0){
            responseInfo.setCode(-1);
            responseInfo.setMsg("没有获取到数据！");
        }else{
            responseInfo.setData(list);
        }
        return responseInfo;
    }

    /**
     * 学员查看未选课程
     * @param pageNum
     * @param session
     * @return
     */
    @GetMapping("/ajax/unselectedCourses")
    @ResponseBody
    public AjaxResponseInfo unselectedCourses(@RequestParam(defaultValue = "1",required = true,value = "pageNum")Integer pageNum,HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //从session中获取当前登录的用户
        UserInfo user = (UserInfo)session.getAttribute("loginedUser");
        if(user==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("请先登录");
        }else{
            int userId = user.getUserId();
            PageInfo<CourseDetailInfo> list = courseService.unselectedCourses(userId,pageNum,5);
            if(list == null || list.getSize()==0){
                responseInfo.setCode(-1);
                responseInfo.setMsg("没有获取到数据！");
            }else{
                responseInfo.setData(list);
            }
        }
        return  responseInfo;
    }

    /**
     * 学员选课
     * @param userCourseInfo
     * @param session
     * @return
     */
    @PostMapping("/ajax/selectCourse")
    @ResponseBody
    public AjaxResponseInfo selectCourse(@RequestBody UserCourseInfo userCourseInfo,HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //从session中获取当前系统登录的用户
        UserInfo user = (UserInfo)session.getAttribute("loginedUser");
        if(user ==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("请先登录！");
        }else{
            //保存数据
            int row = courseService.selectCourse(userCourseInfo);
            if(row>0){
                responseInfo.setMsg("选择课程成功！");
            }else{
                responseInfo.setCode(-1);
                responseInfo.setMsg("选择课程失败！");
            }

        }
        return responseInfo;
    }

    /**
     * 学员查看已选课程
     * @param pageNum
     * @param session
     * @return
     */
    @GetMapping("/ajax/selectedCourses")
    @ResponseBody
    public AjaxResponseInfo selectedCourses(@RequestParam(defaultValue = "1",required = true,value = "pageNum")Integer pageNum,HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //获取当前登录的用户信息
        UserInfo user = (UserInfo)session.getAttribute("loginedUser");
        if(user==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("前先登录");
        }else{
            int userId = user.getUserId();
            PageInfo<CourseDetailInfo> list = courseService.selectedCourses(userId,pageNum,5);
            if(list==null || list.getSize()==0){
                responseInfo.setCode(-1);
                responseInfo.setMsg("没有获取到数据！");
            }else{
                responseInfo.setData(list);
            }
        }
        return responseInfo;
    }
}
