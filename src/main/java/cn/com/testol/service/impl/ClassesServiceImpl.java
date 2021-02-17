package cn.com.testol.service.impl;

import cn.com.testol.dao.ClassesMapper;
import cn.com.testol.dao.User_ClassesMapper;
import cn.com.testol.pojo.Classes;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional  //事务的注解
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private User_ClassesMapper user_classesMapper;

    //根据用户ID查找班级
    @Override
    public List<User_classes> queryClassesByU_id(int u_id) {
        List<User_classes> userClassesList=classesMapper.queryClassesByU_id(u_id);
        for(User_classes u_c : userClassesList){
//            处理班级时间
//            u_c.getUser_classes().setEnter_date(u_c.getUser_classes().getEnter_date().substring(0,10));
            //处理班级加入方式
            switch (u_c.getClasses().getJoinWay()){
                case "all": u_c.getClasses().setJoinWay("允许任何人加入");break;
                case "apply": u_c.getClasses().setJoinWay("需要管理员同意申请");break;
                case "no": u_c.getClasses().setJoinWay("不允许任何人加入");break;
            }

            //处理班级身份
            switch (u_c.getStatus()){
                case "creator": u_c.setStatus("创建者");break;
                case "admin": u_c.setStatus("管理员");break;
                case "student": u_c.setStatus("学生");break;
            }

            //处理创建者
            if(u_id == u_c.getClasses().getCreator_id()){
                u_c.getClasses().setCreatorName("[我自己]");
            }

            //处理班级简介为空的情况
            if(u_c.getClasses().getIntroduction()==""){
                u_c.getClasses().setIntroduction("无");
            }

        }
        return userClassesList;
    }

    //加入班级
    @Override
    public int joinClasses(int u_id, int c_id, String status, String date) {
        User_classes record=user_classesMapper.selectRecord(u_id,c_id);
        if(record!=null){
            return -1;
        }
        int result=0;
        try {
            Classes classes= classesMapper.queryClassesByC_id(c_id).getClasses();
            classes.setPeople_num(classes.getPeople_num()+1);
            classesMapper.updateClasses(classes.getName(),classes.getIntroduction(),classes.getJoinWay(),classes.getPeople_num(),classes.getC_id());
            result=user_classesMapper.addRecord(u_id,c_id,status,date);
            return result;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
    }

    //退出班级
    @Override
    public int outClasses(int u_id, int c_id) {
        int result=0;
        try {
            Classes classes= classesMapper.queryClassesByC_id(c_id).getClasses();
            int people_num=classes.getPeople_num();
            if(people_num>0){
                people_num--;
            }
            classesMapper.updateClasses(classes.getName(),classes.getIntroduction(),classes.getJoinWay(),people_num,classes.getC_id());
            result=user_classesMapper.deleteRecord(u_id,c_id);
            return result;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
    }

    //根据班级ID查找班级
    @Override
    public User_classes queryClassesByC_id(int c_id) {
        User_classes userClasses=classesMapper.queryClassesByC_id(c_id);

        if(userClasses == null){
            return userClasses;
        }

        //处理班级编号
//        String id=classes.getC_id()+"";
//        while (id.length()<5){
//            id="0"+id;
//        }
//        classes.setNumber(id);

        //处理班级加入方式
        switch (userClasses.getClasses().getJoinWay()){
            case "all": userClasses.getClasses().setJoinWay("允许任何人加入");break;
            case "apply": userClasses.getClasses().setJoinWay("需要管理员同意申请");break;
            case "no": userClasses.getClasses().setJoinWay("不允许任何人加入");break;
        }

        //处理班级简介为空的情况
        if(userClasses.getClasses().getIntroduction().equals("")){
            userClasses.getClasses().setIntroduction("无");
        }

        return userClasses;
    }

    //创建班级
    @Override
    public int createClasses(Classes classes ,int u_id) {
        int result=0;
        try{
            classesMapper.createClasses(classes);
            result=user_classesMapper.addRecord(u_id,classes.getC_id(),"creator",classes.getCreate_date());
            return result;
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
    }

    //修改班级信息
    @Override
    public int updateClasses(String name,String introduction,String joinWay,int people_num,int id) {
        return classesMapper.updateClasses(name , introduction, joinWay ,people_num,id);
    }

    //删除班级
    @Override
    public int deleteClasses(int id) {
        return classesMapper.deleteClasses(id);
    }
}
