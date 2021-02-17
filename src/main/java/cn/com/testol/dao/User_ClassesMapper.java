package cn.com.testol.dao;

import cn.com.testol.pojo.User_classes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface User_ClassesMapper {

    @Select("select * from user_classes where u_id=#{u_id} and c_id=#{c_id}")
    public User_classes selectRecord(int u_id,int c_id);

    @Insert("insert into user_classes(u_id,c_id,status,enter_date)" +
            "values(#{u_id},#{c_id},#{status},#{date})")
    public int addRecord(int u_id,int c_id, String status,String date);

    @Delete("delete from user_classes where u_id=#{u_id} and c_id=#{c_id}")
    public int deleteRecord(int u_id,int c_id);
}
