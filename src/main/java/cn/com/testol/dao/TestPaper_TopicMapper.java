package cn.com.testol.dao;

import cn.com.testol.pojo.TestPaper_topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestPaper_TopicMapper {

    @Select("select * from testpaper_topic where tp_id=#{tp_id}")
    public List<TestPaper_topic> getTestPaper_topicByTp_id(int tp_id);

    @Insert("insert into testpaper_topic(tp_id,t_id) value(#{tp_id},#{t_id})")
    public int addRecord(int tp_id , int t_id);

    @Delete("delete from testpaper_topic where tp_id=#{tp_id} and t_id=#{t_id}")
    public int deleteRecord(int tp_id , int t_id);
}
