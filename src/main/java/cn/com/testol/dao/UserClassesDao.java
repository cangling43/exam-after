package cn.com.testol.dao;

import cn.com.testol.entity.UserClasses;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserClasses record);

    int insertSelective(UserClasses record);

    UserClasses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserClasses record);

    int updateByPrimaryKey(UserClasses record);

    UserClasses selectRecord(Integer userId,Integer classesId);

    int deleteRecord(int userId,int classesId);


}