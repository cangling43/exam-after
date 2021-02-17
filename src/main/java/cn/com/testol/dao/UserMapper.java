package cn.com.testol.dao;

import cn.com.testol.pojo.Classes;
import cn.com.testol.pojo.User;
import cn.com.testol.pojo.User_classes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    //登录
    public User loginByEmail(int u_id,String email,String password);
    public User loginByPhone(int u_id,String phone,String password);

    //用户注册
    public int addUserInfo(User user);


    @Insert("insert into user_password(u_id ,password)" +
            "value(#{u_id},#{password})")
    public int addUserPassword(int u_id,String password);


    //根据id查找用户
    public User getUserById(int u_id);
    //根据email查找用户
    public User getUserByEmail(String email);
    //根据phone查找用户
    public User getUserByPhone(String phone);

    //根据班级id查找用户
    public List<User_classes> queryUserByC_id(int c_id);

    //修改用户信息
    @Update("update users set u_name=#{name},sex=#{sex},phone=#{phone},email=#{email},photo=#{photo},status=#{status},work=#{work}" +
            "where u_id=#{id}")
    public int updateUser(int id,String name,String sex,String phone,String email,String photo,String status,String work);

    //注销用户
    @Delete("delete from users where u_id=#{id}")
    public int deleteUser(int id);
}



