package cn.com.testol.service;


import cn.com.testol.DTO.UserClassesDTO;
import cn.com.testol.entity.User;
import cn.com.testol.entity.UserClasses;
import cn.com.testol.utils.Msg;

import java.util.List;

public interface UserService {
    //登陆
    public User login(String name, String password);

    //查找
    public User getUserById(int u_id);
//    public User getUserByEmail(String email);
//    public User getUserByPhone(String phone);
    public List<UserClassesDTO> queryUserByC_id(int c_id);

    //注册
    public Msg addUser(User user,String password);

    public Msg getRole(Integer userId);

    public Msg changeRole(Integer userId);

    //修改用户信息
    public int updateUser(User user);

    //注销用户
    public int deleteUser(int id);
}
