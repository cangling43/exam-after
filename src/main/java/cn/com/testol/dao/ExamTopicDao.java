package cn.com.testol.dao;

import cn.com.testol.entity.ExamTopic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamTopicDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamTopic record);

    int insertSelective(ExamTopic record);

    ExamTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTopic record);

    int updateByPrimaryKey(ExamTopic record);

    List<ExamTopic> selectByExamId(Integer examId);

    int deleteRecord(Integer examId,Integer topicId);
}