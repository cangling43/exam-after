package cn.com.testol.service;

import cn.com.testol.DTO.ReleasExamDTO;
import cn.com.testol.utils.Msg;

public interface ReleaseExamService {

//    public List<TestPaper_classes> getClassesByTp_id(int tp_id);
//    //查询班级内的考试信息
//    public List<TestPaper_classes> getTestpapertByC_id(int c_id,int u_id);

    //发布考试
    public Msg releaseTest(ReleasExamDTO releasExamDTO);

//    public int updateRecord(int tp_id,int c_id,String release_time,String start_date,String deadline);
//
    public int deleteRecord(Integer tp_id,Integer c_id);

    public Msg getReleaseInfo(Integer classesId,Integer examId);

}