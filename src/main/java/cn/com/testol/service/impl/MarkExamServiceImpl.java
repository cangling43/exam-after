package cn.com.testol.service.impl;


import cn.com.testol.DTO.MarkExamInfoDTO;
import cn.com.testol.DTO.StuSubmitExamDTO;
import cn.com.testol.DTO.UserGradeDTO;
import cn.com.testol.dao.*;
import cn.com.testol.entity.*;
import cn.com.testol.service.MarkExamService;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional  //事务的注解
public class MarkExamServiceImpl implements MarkExamService {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private UserTopicDao userTopicDao;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ClassesDao classesDao;

    @Override
    public Msg submitTestPaper(StuSubmitExamDTO stuSubmitExamDTO,Integer userId) {
        try{
            UserGrade userGrade=new UserGrade();
            BeanUtils.copyProperties(stuSubmitExamDTO,userGrade);
            userGrade.setAnswerDate(new Date());
            userGrade.setUserId(userId);
            userGrade.setMarkStatus(0);
            userGrade.setExamStatus(1);

            double grade = 0;

            //总分
            for (UserTopic ut:stuSubmitExamDTO.getUserTopicList()){

                Topic topic = topicDao.selectByPrimaryKey(ut.getTopicId());

                /*判断回答是否正确*/
                //填空题
                if(topic.getTopicType() == 3) {
                    String[] correctAnswerArr = topic.getCorrectAnswer().split("\n");
                    String[] userAnswerArr = ut.getUserAnswer().split("\n");
                    double topicGrade = 0;
                    for(int i=0;i<correctAnswerArr.length;i++){
                        if(correctAnswerArr[i].equals(userAnswerArr[i])){
                            topicGrade = topicGrade + (int) (topic.getScore() / correctAnswerArr.length);
                        }
                    }

                    ut.setUserScore(topicGrade);
                    grade = grade + topicGrade;
                }
                //简答题
                else if(topic.getTopicType() == 4){
                    String[] correctAnswerArr = topic.getCorrectAnswer().split("\n");
                    double topicGrade = 0;
                    for (String ca : correctAnswerArr) {
                        if(ut.getUserAnswer().contains(ca)){
                            topicGrade = topicGrade +  (int) (topic.getScore() / correctAnswerArr.length);
                        }
                    }
                    ut.setUserScore(topicGrade);
                    grade = grade + topicGrade;

                //其他题型
                }else if(topic.getCorrectAnswer().equals(ut.getUserAnswer())){
                    //评判分数
                    ut.setUserScore(topic.getScore());
                    //计算总分
                    grade = grade + topic.getScore();
                }else{
                    ut.setUserScore(0.0);
                }


                //设置为未批改状态
                ut.setTopicStatus("0");
                ut.setUserId(userId);
                userTopicDao.insert(ut);
            }
            userGrade.setGradeAuto(grade);
            System.out.println(userGrade);

            Exam exam = examDao.selectByPrimaryKey(stuSubmitExamDTO.getExamId());
            if (exam.getAutoMack() == 1){
                userGrade.setGrade(grade);
            }

            userGradeDao.insert(userGrade);
            return ResultUtil.success();
        }catch (Exception e){
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg selectByClassesId(Integer classesId, Integer examId, Integer userId) {
        try {
            Classes classes=classesDao.selectByPrimaryKey(classesId);
            if(userId != classes.getCreatorId()){
                return ResultUtil.error(400,"您不是该班级管理员");
            }

            List<UserGrade> userGradeList = userGradeDao.selectByClassesId(classesId,examId );
            //删除创建者数据
            for(int i=0;i<userGradeList.size();i++){
                if(userGradeList.get(i).getUserId() == userId){
                    userGradeList.remove(i);
                }
            }
            return ResultUtil.success(userGradeList);

        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg selestStuExamInfo(Integer classesId, Integer examId, Integer userId) {
        try {
            MarkExamInfoDTO markExamInfoDTO = examDao.selestStuExamInfo(classesId,examId,userId);
            return ResultUtil.success(markExamInfoDTO);
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg tchMarkExam(UserGradeDTO userGradeDTO, Integer userId) {
        try {
            Exam exam = examDao.selectByPrimaryKey(userGradeDTO.getExamId());
            if(exam.getCreatorId() != userId){
                return ResultUtil.error(3004,"您没有权利批改该试卷(身份不正确)");
            }

            UserGrade userGrade = new UserGrade();
            BeanUtils.copyProperties(userGradeDTO,userGrade);
            userGrade.setMarkStatus(1);
            userGrade.setMarkDate(new Date());
            userGradeDao.updateByForeignKeySelective(userGrade);

            for(UserTopic ut:userGradeDTO.getUserTopicList()){
                ut.setTopicStatus("1");
                userTopicDao.updateByForeignKeySelective(ut);
            }
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

}
