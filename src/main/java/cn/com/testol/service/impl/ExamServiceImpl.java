package cn.com.testol.service.impl;

import cn.com.testol.DTO.*;
import cn.com.testol.dao.*;
import cn.com.testol.entity.*;
import cn.com.testol.service.ExamService;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.Page;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional  //事务的注解
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private UserClassesDao userClassesDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private ExamTopicDao examTopicDao;
    @Autowired
    private ExamClassesDao examClassesDao;
    @Autowired
    private UserGradeDao userGradeDao;

    @Override
    public Msg deleteByPrimaryKey(Integer examId) {
        return null;
    }

    @Override
    public Msg insert(ExamTopicTchDTO examTopicTchDTO, Integer userId) {
        try{
            Exam exam = new Exam();
            BeanUtils.copyProperties(examTopicTchDTO,exam);
            exam.setCreatorId(userId);
            exam.setCreateDate(new Date());
            exam.setUpdateDate(new Date());
            examDao.insert(exam);

            Topic t = new Topic();
            for (TopicTchDTO topic:examTopicTchDTO.getTopicTchDTOList()){
                BeanUtils.copyProperties(topic,t);

                t.setCreatorId(userId);
                t.setCreateDate(new Date());
                t.setUpdateDate(new Date());
                t.setSubjectId(exam.getSubjectId());
                t.setSubjectName(exam.getSubjectName());

                topicDao.insert(t);
                examTopicDao.insert(new ExamTopic(null,exam.getExamId(),t.getTopicId(),t.getTopicType()));
            }

            return ResultUtil.success(exam);
        }catch (Exception e){
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,e.toString());
        }
    }


    @Override
    public Msg updateByPrimaryKeySelective(ExamTopicTchDTO examTopicTchDTO,Integer userId) {
        try {

            if (examTopicTchDTO.getCreatorId() != userId){
                return ResultUtil.error(3002,"您没有权利更改该试卷");
            }

            //获取试卷_题目记录
            List<ExamTopic> examTopicList = examTopicDao.selectByExamId(examTopicTchDTO.getExamId());

            //获取旧的试卷id集合
            List<Integer> oldTopicIdList = new ArrayList<Integer>();
            for (ExamTopic tt:examTopicList){
                oldTopicIdList.add(tt.getTopicId());
            }

            //判断更新后的题目是否存在  存在则更新   不存在则添加  剩下没有匹配到的则删除记录
            for (TopicTchDTO t:examTopicTchDTO.getTopicTchDTOList()){

                Topic topic = new Topic();
                BeanUtils.copyProperties(t,topic);

                boolean isHas = false;
                for (int i=0; i<oldTopicIdList.size();i++){
                    if(t.getTopicId() == oldTopicIdList.get(i)){
                        isHas = true;
                        //存在则更新
                        topicDao.updateByPrimaryKeySelective(topic);
                        //移出旧的试卷id集合
                        oldTopicIdList.remove(i);
                        break;
                    }
                }
                //不存在则添加
                if(isHas == false){
                    topic.setCreatorId(examTopicTchDTO.getCreatorId());
                    topic.setSubjectId(examTopicTchDTO.getSubjectId());
                    topic.setSubjectName(examTopicTchDTO.getSubjectName());
                    topic.setUpdateDate(new Date());
                    topic.setCreateDate(new Date());
                    topicDao.insert(topic);
                    examTopicDao.insert(new ExamTopic(null,examTopicTchDTO.getExamId(),topic.getTopicId(),topic.getTopicType()));
                }
            }
            //剩下没有匹配到的则删除
            for (Integer oldT_id:oldTopicIdList){
                examTopicDao.deleteRecord(examTopicTchDTO.getExamId(),oldT_id);
            }

            //修改试卷信息
            Exam exam = new Exam();
            BeanUtils.copyProperties(examTopicTchDTO,exam);
            exam.setUpdateDate(new Date());
            examDao.updateByPrimaryKeySelective(exam);

            return ResultUtil.success();
        }catch (Exception e){
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,e.toString());
        }
    }

    @Override
    public Msg selectByCreatorId(Integer userId, String keyword) {
        List<Exam> examList = examDao.selectByCreatorId(userId,keyword);
        for (Exam e:examList){
            e.setReleasing(0);
            List<ExamClasses> examClassesList = examClassesDao.selectByExamId(e.getExamId());
            if(examClassesList.size() > 0){
                e.setReleasing(1);
            }
        }
        if(examList != null){
            return ResultUtil.success(examList);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @Override
    public Msg tchSelectByPrimaryKey(Integer userId , Integer examId) {
        ExamTopicTchDTO examTopicTchDTO = examDao.tchSelectByPrimaryKey(examId);
        if(examTopicTchDTO == null) {
            return ResultUtil.error(100, "请求失败");
        }
        examTopicTchDTO.setReleasing(0);
        List<ExamClasses> examClassesList = examClassesDao.selectByExamId(examId);
        if(examClassesList.size() > 0){
            examTopicTchDTO.setReleasing(1);
        }
        if(examTopicTchDTO.getCreatorId() != userId) {
            return ResultUtil.error(3001,"您没有权利查看该试卷");
        }
        System.out.println(examTopicTchDTO);
        return ResultUtil.success(examTopicTchDTO);

    }

    @Override
    public Msg stuSelectByPrimaryKey(Integer userId ,Integer classesId , Integer examId) {
        UserClasses userClasses = userClassesDao.selectRecord(userId,classesId);
        if(userClasses == null){
            return ResultUtil.error(3003,"您不是该班级的学生");
        }

        ExamTopicStuDTO examTopicStuDTO = examDao.stuSelectByPrimaryKey(examId,classesId,userId);
        if(examTopicStuDTO == null) {
            return ResultUtil.error(100, "请求失败");
        }
        ReleaseExamDTO examClasses = examClassesDao.selectRecord(classesId,examId);
        //不公布答案
        if(examClasses.getPublishAnswer() != 1 || examTopicStuDTO.getUserGrade().getExamStatus() == null){
            for(TopicTchDTO t:examTopicStuDTO.getTopicTchDTOList()){
                t.setAnalysis(null);
                t.setCorrectAnswer(null);
            }
        }
        //不公布分数
        if(examClasses.getPublishScore() != 1){

            examTopicStuDTO.getUserGrade().setGrade(null);
            examTopicStuDTO.getUserGrade().setGradeAuto(null);
        }
        //不公布答案和分数
        if(examClasses.getPublishScore() != 1 && examClasses.getPublishAnswer() != 1){
            for(UserTopic ut:examTopicStuDTO.getUserTopicList()){
                ut.setUserScore(null);
            }
        }
        //学生未完成试卷
        if(examTopicStuDTO.getUserGrade().getExamStatus() == null){
            examTopicStuDTO.setUserGrade(null);
            examTopicStuDTO.setUserTopicList(null);
        }
        return ResultUtil.success(examTopicStuDTO);
    }

    @Override
    public Msg selectByClassesId(Integer classesId, Integer userId) {
        try {
            List<ExamClassesDTO> examClassesDTOList = examDao.selectByClassesId(classesId, userId);
            return ResultUtil.success(examClassesDTOList);
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg selectFinishExamList(Integer userId, int pageSize, int currentPage) {
        List<UserGrade> userGradeDTOList = userGradeDao.selectByUserId(userId);
        for(UserGrade ug:userGradeDTOList){
            ReleaseExamDTO examClasses = examClassesDao.selectRecord(ug.getClassesId(),ug.getExamId());
            if(examClasses != null &&examClasses.getPublishScore() != 1){
                ug.setGrade(null);
            }
        }
        Page<UserGrade> page = new Page<UserGrade>(pageSize,currentPage);
        page.build(userGradeDTOList);
        return ResultUtil.success(page);
    }


}
