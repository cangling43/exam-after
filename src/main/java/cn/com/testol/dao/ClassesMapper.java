package cn.com.testol.dao;


import cn.com.testol.pojo.Classes;
import cn.com.testol.pojo.User_classes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassesMapper {

    //根据用户id查找班级
    public List<User_classes> queryClassesByU_id(int u_id);

    //根据班级id查找班级
//    @Select("select * from classes where c_id=#{c_id}")
    public User_classes queryClassesByC_id(int c_id);


    //创建班级
//    @Insert("insert into classes(name ,create_date ,introduction ,creator_id ,people_num ,joinWay)"+
//                " value(#{name} ,#{create_date} ,#{introduction} ,#{creator_id} ,#{people_num} ,#{joinWay})")
    public int createClasses(Classes classes);

    //修改班级信息
    @Update("update classes set c_name=#{name} ,introduction=#{introduction},joinWay=#{joinWay} ,people_num=#{people_num} " +
            "where c_id=#{id} ")
    public int updateClasses(String name,String introduction,String joinWay,int people_num,int id);

    //删除班级
    @Delete("delete from classes where c_id=#{id}")
    public int deleteClasses(int id);
}

