package cn.com.testol.service;


import cn.com.testol.DTO.ExamTopicTchDTO;
import cn.com.testol.utils.Msg;

public interface ExamService {
    Msg deleteByPrimaryKey(Integer examId);

    //创建试卷
    Msg insert(ExamTopicTchDTO examTopicTchDTO , Integer userId);

    //修改试卷
    Msg updateByPrimaryKeySelective(ExamTopicTchDTO examTopicTchDTO,Integer userId);

    //查用户下创建的试卷
    Msg selectByCreatorId(Integer userId,String keyword);

    //查看试卷(老师)
    Msg tchSelectByPrimaryKey(Integer userId , Integer examId);

    //查看试卷(学生)
    Msg stuSelectByPrimaryKey(Integer userId , Integer classesId , Integer examId);

    Msg selectByClassesId(Integer classesId ,Integer userId);

    Msg selectFinishExamList(Integer userId,int pageSize,int currentPage);
}
