package cn.com.testol.service.impl;

import cn.com.testol.DTO.ClassesExamDTO;
import cn.com.testol.DTO.ClassesUserDTO;
import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.ExamClassesDao;
import cn.com.testol.dao.UserClassesDao;
import cn.com.testol.dao.UserGradeDao;
import cn.com.testol.entity.Classes;
import cn.com.testol.entity.UserClasses;
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
    private ExamClassesDao examClassesDao;
    @Autowired
    private UserGradeDao userGradeDao;

    //根据用户ID查找班级
    @Override
    public Msg queryClassesByU_id(Integer u_id) {
        try {
            List<ClassesUserDTO> userClassesList = classesDao.selectByUserId(u_id);

            for(ClassesUserDTO u_c : userClassesList){
//            处理班级时间
//            u_c.getUser_classes().setEnter_date(u_c.getUser_classes().getEnter_date().substring(0,10));
                //处理班级加入方式
                switch (u_c.getJoinway()){
                    case "all": u_c.setJoinway("允许任何人加入");break;
                    case "apply": u_c.setJoinway("需要管理员同意申请");break;
                    case "no": u_c.setJoinway("不允许任何人加入");break;
                }

                //处理班级身份
                switch (u_c.getPosition()){
                    case "creator": u_c.setPosition("创建者");break;
                    case "admin": u_c.setPosition("管理员");break;
                    case "student": u_c.setPosition("学生");break;
                }

                //处理创建者
                if(u_id == u_c.getCreatorId()){
                    u_c.setCreatorName("[我自己]");
                }

            }
            System.out.println(userClassesList);
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
            if(people_num > 0){
                people_num--;
            }
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


    //创建班级
    @Override
    public Msg createClasses(Classes classes, Integer userId) {
        try{
            classes.setUpdateDate(new Date());
            classes.setCreateDate(new Date());
            classes.setPeopleNum(1);
            classes.setCreatorId(userId);
            classesDao.insert(classes);
            userClassesDao.insert(new UserClasses(null,userId,classes.getClassesId(),"creator",new Date()));
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

            Boolean updateSuccess =  updateClassesName(classes.getClassesName(),classes.getClassesId());
            if(updateSuccess){
                return ResultUtil.success();
            }else{
                return ResultUtil.error(100,"请求失败");
            }
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

    @Override
    public Boolean updateClassesName(String name, Integer classesId) {
        try{
            examClassesDao.updateClassesName(name,classesId);
            userGradeDao.updateClassesName(name,classesId);
            return true;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }


}
