package cn.com.testol.dao;

import cn.com.testol.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopicDao {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}