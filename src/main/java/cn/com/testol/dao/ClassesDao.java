package cn.com.testol.dao;

import cn.com.testol.DTO.ClassesExamDTO;
import cn.com.testol.DTO.ClassesUserDTO;
import cn.com.testol.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassesDao {
    int deleteByPrimaryKey(Integer classesId);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classesId);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> fuzzyQuery(String keyword);

    List<ClassesUserDTO> selectByUserId(Integer userId,String keyword);

    List<ClassesUserDTO> selectByCreatorId(Integer userId,String keyword);

    List<ClassesExamDTO> selectByExamId(Integer examId);


}