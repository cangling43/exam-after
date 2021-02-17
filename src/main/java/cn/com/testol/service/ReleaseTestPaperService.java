package cn.com.testol.service;

import cn.com.testol.pojo.TestPaper_classes;

import java.util.List;

public interface ReleaseTestPaperService {

    public List<TestPaper_classes> getClassesByTp_id(int tp_id);
    //查询班级内的考试信息
    public List<TestPaper_classes> getTestpapertByC_id(int c_id,int u_id);

    //发布考试
    public int releaseTest(int tp_id,int[] c_id,String release_time,String start_date,String deadline);

    public int updateRecord(int tp_id,int c_id,String release_time,String start_date,String deadline);

    public int deleteRecord(int tp_id,int c_id);

}
