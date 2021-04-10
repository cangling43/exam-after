package cn.com.testol.service;

import cn.com.testol.DTO.StuSubmitExamDTO;
import cn.com.testol.DTO.UserGradeDTO;
import cn.com.testol.utils.Msg;

public interface MarkExamService {
    public Msg submitTestPaper(StuSubmitExamDTO stuSubmitExamDTO,Integer userId);

    public Msg selectByClassesId(Integer classesId,Integer examId,Integer userId);

    public Msg selestStuExamInfo(Integer classesId, Integer examId, Integer userId);

    //教师批改试卷
    public Msg tchMarkExam(UserGradeDTO userGradeDTO, Integer userId);
}
