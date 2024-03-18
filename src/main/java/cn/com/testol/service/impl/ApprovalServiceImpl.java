package cn.com.testol.service.impl;

import cn.com.testol.DTO.ApprovalDTO;
import cn.com.testol.dao.ApprovalDao;
import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.UserClassesDao;
import cn.com.testol.entity.Approval;
import cn.com.testol.entity.Classes;
import cn.com.testol.entity.UserClasses;
import cn.com.testol.enums.Position;
import cn.com.testol.service.ApprovalService;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.Page;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional  //事务的注解
public class ApprovalServiceImpl implements ApprovalService {
    @Autowired
    private ApprovalDao approvalDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private UserClassesDao userClassesDao;

    @Override
    public Msg getApprovalList(Integer tchId,int pageSize,int currentPage) {
        List<ApprovalDTO> approvalList = approvalDao.selectByTchId(tchId);
        Page<ApprovalDTO> page = new Page<ApprovalDTO>(pageSize,currentPage);
        page.build(approvalList);
        return ResultUtil.success(page);
    }

    @Override
    public Msg approval(Integer tchId, Integer approvalId, Integer status) {
        try {
            Approval approval = approvalDao.selectByPrimaryKey(approvalId);
            approval.setStatus(status);
            Classes classes = classesDao.selectByPrimaryKey(approval.getClassesId());
            if(!classes.getCreatorId().equals(tchId)){
                ResultUtil.error(400,"您不是该班级管理员");
            }
            if(status == 1){
                classes.setPeopleNum(classes.getPeopleNum()+1);
                classesDao.updateByPrimaryKeySelective(classes);
                userClassesDao.insert(new UserClasses(null,approval.getStudentId(),approval.getClassesId(), Position.student.getValue(),new Date()));
            }
            approvalDao.updateByPrimaryKeySelective(approval);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

}
