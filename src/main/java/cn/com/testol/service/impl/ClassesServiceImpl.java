package cn.com.testol.service.impl;

import cn.com.testol.DTO.ClassesExamDTO;
import cn.com.testol.DTO.ClassesUserDTO;
import cn.com.testol.dao.ApprovalDao;
import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.UserClassesDao;
import cn.com.testol.entity.Approval;
import cn.com.testol.entity.Classes;
import cn.com.testol.entity.UserClasses;
import cn.com.testol.enums.Role;
import cn.com.testol.service.ClassesService;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional  //事务的注解
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private UserClassesDao userClassesDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private ApprovalDao approvalDao;

    //根据用户ID查找班级
    @Override
    public Msg queryClassesByU_id(Integer u_id, String role, String keyword) {
        try {
            System.out.println(role);
            List<ClassesUserDTO> userClassesList = null;
            if(role.equals(Role.student.getValue())){
                userClassesList = classesDao.selectByUserId(u_id,keyword);
            }
            if(role.equals(Role.teacher.getValue())){
                userClassesList = classesDao.selectByCreatorId(u_id,keyword);
            }
            
            return ResultUtil.success(userClassesList);
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    //加入班级
    @Override
    public Msg joinClasses(int u_id, int c_id, String status, Date date) {
        try {
            Classes classes = classesDao.selectByPrimaryKey(c_id);
            if(classes==null){
                return ResultUtil.error(2001,"该班级不存在");
            }
            if(classes.getJoinway().equals("no")){
                return ResultUtil.error(2005,"该班级不允许进入,请与班级的管理员联系");
            }
            UserClasses record=userClassesDao.selectRecord(u_id,c_id);
            if(record!=null){
                return ResultUtil.error(2002,"已加入该班级");
            }
            if(classes.getJoinway().equals("apply")){
                Approval recovrd = approvalDao.selectByRecord(u_id,c_id);
                if(recovrd == null){
                    Approval approval = new Approval();
                    approval.setStudentId(u_id);
                    approval.setTeacherId(classes.getCreatorId());
                    approval.setClassesId(c_id);
                    approval.setApplyDate(new Date());
                    approval.setStatus(0);
                    approvalDao.insert(approval);
                }else if(recovrd.getStatus() == 2 || recovrd.getStatus() == 1){
                    recovrd.setStatus(0);
                    recovrd.setApplyDate(new Date());
                    approvalDao.updateByPrimaryKeySelective(recovrd);
                }else if(recovrd.getStatus() == 0){
                    recovrd.setApplyDate(new Date());
                    approvalDao.updateByPrimaryKeySelective(recovrd);
                }

                return ResultUtil.success();
            }

            classes.setPeopleNum(classes.getPeopleNum()+1);
            classesDao.updateByPrimaryKeySelective(classes);
            userClassesDao.insert(new UserClasses(null,u_id,c_id,status,date));
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    //退出班级
    @Override
    public Msg outClasses(int u_id, int c_id) {
        try {
            Classes classes= classesDao.selectByPrimaryKey(c_id);
            int people_num = classes.getPeopleNum();
            classes.setPeopleNum(people_num-1);
            classesDao.updateByPrimaryKeySelective(classes);
            userClassesDao.deleteRecord(u_id,c_id);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }

    }

    @Override
    public Msg queryClassesByC_id(int c_id) {
        Classes classes = classesDao.selectByPrimaryKey(c_id);
        if(classes != null){
            return ResultUtil.success(classes);
        }else{
            return ResultUtil.error(2001,"该班级不存在");
        }
    }

    @Override
    public Msg queryClassesByExamId(Integer examId) {
        try {
            List<ClassesExamDTO> classesExamDTOList = classesDao.selectByExamId(examId);
            return ResultUtil.success(classesExamDTOList);
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg fuzzyQuery(String keyword) {
        List<Classes> classesList = classesDao.fuzzyQuery(keyword);
        if (classesList != null){
            return ResultUtil.success(classesList);
        }
        return ResultUtil.error(100,"请求失败");
    }


    //创建班级
    @Override
    public Msg createClasses(Classes classes, Integer userId) {
        try{
            classes.setUpdateDate(new Date());
            classes.setCreateDate(new Date());
            classes.setPeopleNum(0);
            classes.setCreatorId(userId);
            classesDao.insert(classes);
//            userClassesDao.insert(new UserClasses(null,userId,classes.getClassesId(),"creator",new Date()));
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

        //修改班级信息
    @Override
    public Msg updateClasses(Classes classes, Integer userId) {
        try{
            if(userId != classesDao.selectByPrimaryKey(classes.getClassesId()).getCreatorId()){
                return ResultUtil.error(400,"您不是该班级管理员");
            }
            System.out.println(classes);
            classesDao.updateByPrimaryKeySelective(classes);

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }



    //删除班级
    @Override
    public int deleteClasses(int id) {

        return classesDao.deleteByPrimaryKey(id);
    }

}
