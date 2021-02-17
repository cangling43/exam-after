package cn.com.testol.dao;

import cn.com.testol.pojo.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TopicMapper {
    public Topic getTopicByt_id(int t_id);

    //创建题目
    public int createTopic(Topic topic);

    //更改题目
    @Update("update topic set subject_id=#{subject_id},question=#{question},choice=#{choice}," +
                "photo=#{photo},topic_type=#{topic_type},correct_answer=#{correct_answer},t_score=#{score}," +
                "difficulty=#{difficulty},analysis=#{analysis} " +
            " where t_id=#{t_id}")
    public int updateTopic(Topic topic);

    //删除题目
    @Delete("delete from topic where t_id=#{t_id}")
    public int deleteTopic(Integer t_id);

}