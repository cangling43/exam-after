package cn.com.testol.service;

import cn.com.testol.entity.Classes;
import cn.com.testol.utils.Msg;

import java.util.Date;

public interface ClassesService  {

    //根据教师查找班级
    public Msg queryClassesByU_id(Integer u_id, String role, String keyword);

    //用户加入班级
    public Msg joinClasses(int u_id, int c_id, String status, Date date);

    //用户退出班级
    public Msg outClasses(int u_id, int c_id);

    //根据班级id查找班级
    public Msg queryClassesByC_id(int c_id);

    public Msg queryClassesByExamId(Integer examId);

    public Msg fuzzyQuery(String keyword);

    //创建班级
    public Msg createClasses(Classes classes,Integer userId);

    //修改班级信息
    public Msg updateClasses(Classes classes,Integer userId);

    //删除班级
    public int deleteClasses(int id);


}
