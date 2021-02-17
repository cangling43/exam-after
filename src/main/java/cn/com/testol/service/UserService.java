package cn.com.testol.service;

import cn.com.testol.pojo.User;
import cn.com.testol.pojo.User_classes;

import java.util.List;

public interface UserService {
    //登陆
    public User login(String name,String password);

    //查找
    public User getUserById(int u_id);
    public User getUserByEmail(String email);
    public User getUserByPhone(String phone);
    public List<User_classes> queryUserByC_id(int c_id);

    //注册
    public int addUser(User user,String password);


    //修改用户信息
    public int updateUser(int id,String name,String sex,String phone,String email,String photo,String status,String work);

    //注销用户
    public int deleteUser(int id);
}
