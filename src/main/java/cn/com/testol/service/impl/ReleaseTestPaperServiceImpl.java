package cn.com.testol.service.impl;

import cn.com.testol.dao.ReleaseTestPaperMapper;
import cn.com.testol.pojo.TestPaper_classes;
import cn.com.testol.service.ReleaseTestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional  //事务的注解
public class ReleaseTestPaperServiceImpl implements ReleaseTestPaperService {
    @Autowired
    private ReleaseTestPaperMapper releaseTestPaperMapper;

    @Override
    public List<TestPaper_classes> getClassesByTp_id(int tp_id) {
        return releaseTestPaperMapper.getClassesByTp_id(tp_id);
    }

    @Override
    public List<TestPaper_classes> getTestpapertByC_id(int c_id,int u_id) {
        List<TestPaper_classes> testPaperClassesList = releaseTestPaperMapper.getTestpapertByC_id(c_id,u_id);
        for(TestPaper_classes tc:testPaperClassesList){
            if(tc.getTestPaper().getUserGrade().getId() == 0){
                tc.getTestPaper().setUserGrade(null);
                continue;
            }
            if(tc.getPublishScore() == 0){
                tc.getTestPaper().getUserGrade().setGrade(-1);
                tc.getTestPaper().getUserGrade().setGradeAuto(-1);
            }
        }
        return testPaperClassesList;
    }

    @Override
    public int releaseTest(int tp_id, int[] c_id, String release_time, String start_date, String deadline) {
        List<TestPaper_classes> testPaperClassesList=releaseTestPaperMapper.getClassesByTp_id(tp_id);
        //未发布的班级id
        List<Integer> addRecord=new ArrayList<Integer>();
        //已发布的班级id
        List<Integer> updateRecord=new ArrayList<Integer>();
        //按是否已发布分类
        for (int i = 0; i < c_id.length; i++) {
            Boolean isHas = false;
            for (TestPaper_classes tc:testPaperClassesList) {
                if(c_id[i] == tc.getC_id()){
                    updateRecord.add(c_id[i]);
                    isHas = true;
                    break;
                }
            }
            if(!isHas){
                addRecord.add(c_id[i]);
            }

        }

        try{
            for (Integer i:addRecord){
                releaseTestPaperMapper.releaseTest(tp_id,i,release_time,start_date,deadline);
            }
            for (Integer i:updateRecord){
                releaseTestPaperMapper.updateRecord(tp_id,i,release_time,start_date,deadline);
            }
            return 1;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }

    @Override
    public int updateRecord(int tp_id, int c_id,String release_time, String start_date, String deadline) {
        return releaseTestPaperMapper.updateRecord(tp_id, c_id,release_time, start_date, deadline);
    }

    @Override
    public int deleteRecord(int tp_id, int c_id) {
        return releaseTestPaperMapper.deleteRecord(tp_id, c_id);
    }
}
