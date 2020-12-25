package com.yueqian.xk.service;

import com.yueqian.xk.beans.UserInfo;

/**
 * 用户业务接口
 */
public interface UserService {
    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    public UserInfo login(UserInfo userInfo);
}
