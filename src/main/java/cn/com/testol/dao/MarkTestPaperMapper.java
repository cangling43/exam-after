package cn.com.testol.dao;

import cn.com.testol.pojo.UserGrade;
import cn.com.testol.pojo.User_topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MarkTestPaperMapper {

    //查询学生成绩
    @Select("select * from user_grade where u_id=#{u_id} and c_id=#{c_id} and tp_id=#{tp_id}")
    public UserGrade getUserGrade(int tp_id,int c_id,int u_id);

    //添加学生答题记录
    @Insert("insert into user_topic(u_id ,c_id,t_id,tp_id,user_answer,ut_score,ut_status) " +
            "value(#{u_id},#{c_id},#{t_id},#{tp_id},#{user_answer},#{score},#{status})")
    public int submitTestPaper(User_topic userTopic);

    //添加学生成绩
    @Insert("insert into user_grade(u_id,c_id,tp_id,grade,grade_auto,answer_date,answer_time,ug_status) " +
            "value(#{u_id},#{c_id},#{tp_id},#{grade},#{gradeAuto},#{date},#{answerTime},#{status})")
    public int markTestPaper(UserGrade userGrade);

    //批改学生题目成绩
    @Update("update user_topic set ut_score=#{score} " +
            "where u_id=#{u_id} and c_id=#{c_id} and tp_id=#{tp_id}")
    public int markTopic(int u_id, int t_id,int tp_id,double score);

    //修改学生成绩
    @Update("update user_grade set grade=#{grade} " +
            "where u_id=#{u_id} and c_id=#{c_id} and tp_id=#{tp_id}")
    public int updateGrade(int u_id, int c_id,int tp_id,double grade);
}

