package cn.com.testol.dao;

import cn.com.testol.pojo.TestPaper_classes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReleaseTestPaperMapper {

    //根据试卷id查询班级
    public List<TestPaper_classes> getClassesByTp_id(int tp_id);
    //查询班级内的考试信息
    public List<TestPaper_classes> getTestpapertByC_id(int c_id,int u_id);

    //发布考试
    @Insert("insert into testpaper_classes(tp_id,c_id,release_time,start_date,deadline)" +
            "value(#{tp_id},#{c_id},#{release_time},#{start_date},#{deadline})")
    public int releaseTest(int tp_id,int c_id,String release_time,String start_date,String deadline);

    //修改记录信息
    @Update("update testpaper_classes set start_date=#{start_date},deadline=#{deadline} ,release_time=#{release_time}" +
            "where tp_id=#{tp_id} and c_id=#{c_id}")
    public int updateRecord(int tp_id,int c_id,String release_time,String start_date,String deadline);

    //删除记录
    @Delete("delete from testpaper_classes where tp_id=#{tp_id} and c_id=#{c_id}")
    public int deleteRecord(int tp_id,int c_id);


}