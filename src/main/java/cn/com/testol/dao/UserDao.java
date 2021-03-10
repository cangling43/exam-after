package cn.com.testol.dao;

import cn.com.testol.DTO.UserClassesDTO;
import cn.com.testol.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    User selectByPhone(String phone);

    List<UserClassesDTO> selectByC_id(Integer classesId);

    User loginByEmail(Integer userId, String email, String password);

    User loginByPhone(Integer userId, String phone, String password);
}