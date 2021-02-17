package cn.com.testol.dao;

import cn.com.testol.pojo.TestPaper;
import cn.com.testol.pojo.TestPaper_classes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestPaperMapper {

    @Select("select * from testpaper where tp_id=#{tp_id}")
    public TestPaper getTestPaper(int tp_id);

    //通过试卷id获取试卷信息(教师角色)
    public TestPaper getTestPaperByTp_id(int tp_id);

    //通过试卷id,班级id,学生id获取试卷信息(学生角色)
    public TestPaper_classes getTestPaperByStu(int tp_id,int c_id,int u_id);

    //获取用户创建的所有试卷
    public List<TestPaper> getTestPaperByU_id(int u_id);

    //创建试卷
    public int createTestPaper(TestPaper testPaper);

    //更改试卷信息
    @Update("update testpaper set tp_name=#{name},time=#{time},create_date=#{create_date},subject_id=#{subject_id}," +
                "topic_num=#{topic_num},total_score=#{total_score},pass_mark=#{pass_mark},permit_copy=#{permit_copy}," +
                "disrupt_order=#{disrupt_order},repeat_test=#{repeat_test},auto_mack=#{auto_mack} " +
            "where tp_id=#{tp_id}")
    public int updateTestPaper(TestPaper testPaper);
}

