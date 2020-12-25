package com.yueqian.xk.mapper;

import com.yueqian.xk.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户dao
 */
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     * @param user
     * @return
     */
    @Select(value="select * from users where username=#{username} and password=#{password}")
    UserInfo login(UserInfo user);


}
