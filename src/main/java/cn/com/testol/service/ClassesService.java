package cn.com.testol.service;

import cn.com.testol.pojo.Classes;
import cn.com.testol.pojo.User_classes;

import java.util.List;

public interface ClassesService {

    //根据教师查找班级
    public List<User_classes> queryClassesByU_id(int u_id);

    //用户加入班级
    public int joinClasses(int u_id, int c_id, String status, String date);

    //用户退出班级
    public int outClasses(int u_id, int c_id);

    //根据班级id查找班级
    public User_classes queryClassesByC_id(int c_id);

    //创建班级
    public int createClasses(Classes classes ,int u_id);

    //修改班级信息
    public int updateClasses(String name,String introduction,String joinWay,int people_num,int id);

    //删除班级
    public int deleteClasses(int id);



}
