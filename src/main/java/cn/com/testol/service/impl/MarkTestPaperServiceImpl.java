package cn.com.testol.service.impl;


import cn.com.testol.utils.JwtUtil;
import cn.com.testol.dao.MarkTestPaperMapper;
import cn.com.testol.dao.TestPaperMapper;
import cn.com.testol.dao.TopicMapper;
import cn.com.testol.pojo.*;
import cn.com.testol.service.MarkTestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional  //事务的注解
public class MarkTestPaperServiceImpl implements MarkTestPaperService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private MarkTestPaperMapper markTestPaperMapper;
    @Autowired
    private TestPaperMapper testPaperMapper;

    @Override
    public int submitTestPaper(SubmitTestPaper submitTestPaper) {

        int u_id=Integer.parseInt(JwtUtil.getUserId(submitTestPaper.getToken()));
        //设置u_id
        for(User_topic ut:submitTestPaper.getUserTopics()){
            ut.setU_id(u_id);
        }

        UserGrade userGrade=new UserGrade();
        userGrade.setU_id(u_id);
        userGrade.setC_id(submitTestPaper.getC_id());
        userGrade.setTp_id(submitTestPaper.getUserTopics().get(0).getTp_id());
        userGrade.setDate(submitTestPaper.getDate());
        userGrade.setAnswerTime(submitTestPaper.getTime());
        userGrade.setStatus(0);

        TestPaper testPaper = testPaperMapper.getTestPaperByTp_id(submitTestPaper.getUserTopics().get(0).getTp_id());

            double grade = 0;
        try{
            //总分
            for (User_topic ut:submitTestPaper.getUserTopics()){

                Topic topic = topicMapper.getTopicByt_id(ut.getT_id());
                System.out.println(topic.toString());





                System.out.println("111");
                //判断回答是否正确
                if(topic.getCorrect_answer().equals(ut.getUser_answer())){
                    //评判分数
                    ut.setScore(topic.getScore());
                    //计算总分
                    grade = grade + topic.getScore();
                }else{
                    ut.setScore(0);
                }
                //设置为未批改状态
                ut.setStatus(0);
                markTestPaperMapper.submitTestPaper(ut);
            }

            userGrade.setGradeAuto(grade);
            if (testPaper.getAuto_mack() == 1){
                userGrade.setGrade(grade);
            }

            markTestPaperMapper.markTestPaper(userGrade);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return 0;
    }


}
