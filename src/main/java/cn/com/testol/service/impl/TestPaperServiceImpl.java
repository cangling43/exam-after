package cn.com.testol.service.impl;

import cn.com.testol.dao.MarkTestPaperMapper;
import cn.com.testol.dao.TestPaperMapper;
import cn.com.testol.dao.TestPaper_TopicMapper;
import cn.com.testol.dao.TopicMapper;
import cn.com.testol.pojo.*;
import cn.com.testol.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional  //事务的注解
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    private TestPaperMapper testPaperMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TestPaper_TopicMapper testPaper_topicMapper;
    @Autowired
    private MarkTestPaperMapper markTestPaperMapper;

    @Override
    public TestPaper getTestPaper(int tp_id) {
        return testPaperMapper.getTestPaper(tp_id);
    }

    //通过试卷id获取试卷信息(教师角色)
    @Override
    public TestPaper getTestPaperByTp_id(int tp_id) {
        TestPaper testPaper=testPaperMapper.getTestPaperByTp_id(tp_id);
        return testPaper;
    }

    @Override
    public TestPaper_classes getTestPaperByStu(int tp_id,int c_id,int u_id) {
        //判断学生是否提交过试卷
        UserGrade userGrade=markTestPaperMapper.getUserGrade(tp_id,c_id,u_id);

//        System.out.println(userGrade.toString());
        TestPaper_classes testPaperClasses;
        if(userGrade == null){
            testPaperClasses=testPaperMapper.getTestPaperByStu(tp_id, c_id,-1);
            testPaperClasses.getTestPaper().setUserGrade(null);
            testPaperClasses.getTestPaper().setUserTopic(null);
        }else {
            testPaperClasses=testPaperMapper.getTestPaperByStu(tp_id,c_id,u_id);
        }
        return testPaperClasses;
    }

    @Override
    public List<TestPaper> getTestPaperByU_id(int u_id) {
        List<TestPaper> testPaperList=testPaperMapper.getTestPaperByU_id(u_id);

        return testPaperList;
    }

    @Override
    public int createTestPaper(TestPaper testPaper , List<Topic> topicList) {
        try{
            //创建试卷
            testPaperMapper.createTestPaper(testPaper);
            for (Topic t : topicList){
                //创建题目
                topicMapper.createTopic(t);
                //添加试卷_题目关联记录
                testPaper_topicMapper.addRecord(testPaper.getTp_id(),t.getT_id());
            }
            return testPaper.getTp_id();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return 0;
    }

    @Override
    public int updateTestPaper(TestPaper testPaper, List<Topic> topicList) {
        try{
            //删除题目
            //删除试卷_题目关联记录
            //修改试卷信息
            //添加题目
            //添加试卷_题目关联记录


            //获取试卷_题目记录
            List<TestPaper_topic> testPaper_topicList=testPaper_topicMapper.getTestPaper_topicByTp_id(testPaper.getTp_id());
            //获取旧的试卷id集合
            List<Integer> oldT_idList = new ArrayList<Integer>();
            for (TestPaper_topic tt:testPaper_topicList){
                oldT_idList.add(tt.getT_id());
            }
            //判断更新后的题目是否存在  存在则更新   不存在则添加  剩下没有匹配到的则删除记录
            for (Topic t:topicList){
                boolean isHas = false;
                for (int i=0; i<oldT_idList.size();i++){
                    if(t.getT_id() == oldT_idList.get(i)){
                        isHas = true;
                        //存在则更新
                        topicMapper.updateTopic(t);
                        //移出旧的试卷id集合
                        oldT_idList.remove(i);
                        break;
                    }
                }
                //不存在则添加
                if(isHas == false){
                    topicMapper.createTopic(t);
                    testPaper_topicMapper.addRecord(testPaper.getTp_id(),t.getT_id());
                }
            }
            //剩下没有匹配到的则删除
            for (Integer oldT_id:oldT_idList){
                //topicMapper.deleteTopic(oldT_id);
                testPaper_topicMapper.deleteRecord(testPaper.getTp_id(),oldT_id);
            }
            //修改试卷信息
            testPaperMapper.updateTestPaper(testPaper);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return 0;
    }
}
