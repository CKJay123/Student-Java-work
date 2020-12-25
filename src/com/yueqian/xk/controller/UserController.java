package com.yueqian.xk.controller;

import com.yueqian.xk.beans.UserInfo;
import com.yueqian.xk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userInfo:接收前台数据
     * @param model：给前台传递数据
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(UserInfo userInfo, Model model, HttpSession session){
       UserInfo user = userService.login(userInfo);
       if(user==null){
           //账号不正确
           model.addAttribute("msg","账号不正确");
           return "login";//login.jsp
       }
       if(user.getUsername().equals("")|| user.getPassword().equals("")){
           //账号不正确
           model.addAttribute("msg","账号不正确");
           return "login";//login.jsp
       }
       if(user.getPassword().equals(userInfo.getPassword())){
           //登录成功，将用户保存到session中
           session.setAttribute("loginedUser",user);
           return "redirect:/main.jsp";
       }
       model.addAttribute("msg","用户名或者密码不正确");
       return "login";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清理session
        session.invalidate();
        return "redirect:/login.jsp";
    }


}
