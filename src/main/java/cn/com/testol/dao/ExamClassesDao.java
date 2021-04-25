package cn.com.testol.dao;

import cn.com.testol.DTO.ReleaseExamDTO;
import cn.com.testol.entity.ExamClasses;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamClasses record);

    int insertSelective(ExamClasses record);

    ExamClasses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamClasses record);

    int updateByPrimaryKey(ExamClasses record);

    List<ExamClasses> selectByExamId(Integer examId);

    int deleteRecord(int examId,int classesId);

    int updateRecord(ExamClasses record);

    ReleaseExamDTO selectRecord(Integer classesId, Integer examId);


}