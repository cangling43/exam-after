package cn.com.testol.service;

import cn.com.testol.pojo.TestPaper;
import cn.com.testol.pojo.TestPaper_classes;
import cn.com.testol.pojo.Topic;

import java.util.List;

public interface TestPaperService {

    public TestPaper getTestPaper(int tp_id);

    //通过试卷id获取试卷信息(教师角色)
    public TestPaper getTestPaperByTp_id(int tp_id);

    //通过试卷id,班级id获取试卷信息(学生角色)
    public TestPaper_classes getTestPaperByStu(int tp_id,int c_id,int u_id);

    //根据用户id查找试卷
    public List<TestPaper> getTestPaperByU_id(int u_id);

    //创建试卷
    public int createTestPaper(TestPaper testPaper, List<Topic> topics);

    //修改试卷
    public int updateTestPaper(TestPaper testPaper, List<Topic> topics);
}
