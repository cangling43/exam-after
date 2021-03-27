package cn.com.testol.service.impl;

import cn.com.testol.DTO.ReleasExamDTO;
import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.ExamClassesDao;
import cn.com.testol.entity.Classes;
import cn.com.testol.entity.ExamClasses;
import cn.com.testol.service.ReleaseTestPaperService;
import cn.com.testol.utils.Msg;
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
public class ReleaseTestPaperServiceImpl implements ReleaseTestPaperService {
    @Autowired
    private ExamClassesDao examClassesDao;


    @Override
    public Msg releaseTest(ReleasExamDTO releasExamDTO) {
        try{
            List<ExamClasses> examClassesList=examClassesDao.selectByExamId(releasExamDTO.getExamId());
            //未发布的班级id
            List<Integer> addRecord=new ArrayList<Integer>();
            //已发布的班级id
            List<Integer> updateRecord=new ArrayList<Integer>();
            //按是否已发布分类
            for (int i = 0; i < releasExamDTO.getClassesId().length; i++) {
                Boolean isHas = false;
                for (ExamClasses ec:examClassesList) {
                    if(releasExamDTO.getClassesId()[i] == ec.getClassesId()){
                        updateRecord.add(releasExamDTO.getClassesId()[i]);
                        isHas = true;
                        break;
                    }
                }
                if(!isHas){
                    addRecord.add(releasExamDTO.getClassesId()[i]);
                }

            }

            ExamClasses examClasses = new ExamClasses();
            BeanUtils.copyProperties(releasExamDTO,examClasses);

            examClasses.setStatus(0);
            examClasses.setReleaseTime(new Date());
            examClasses.setPublishAnswer(0);
            examClasses.setPublishScore(0);

            for (Integer i:addRecord){
                examClasses.setClassesId(i);
                examClassesDao.insert(examClasses);
            }
            for (Integer i:updateRecord){
                examClasses.setClassesId(i);
                examClassesDao.updateByPrimaryKeySelective(examClasses);
            }
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

//    @Override
//    public int updateRecord(int tp_id, int c_id,String release_time, String start_date, String deadline) {
//        return releaseTestPaperMapper.updateRecord(tp_id, c_id,release_time, start_date, deadline);
//    }

    @Override
    public int deleteRecord(Integer examId, Integer classesId) {
        return examClassesDao.deleteRecord(examId, classesId);
    }

    @Override
    public Msg getReleaseInfo(Integer classesId, Integer examId) {
        try{
            ExamClasses examClasses = examClassesDao.selectRecord(classesId,examId);
            return ResultUtil.success(examClasses);
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }
}
