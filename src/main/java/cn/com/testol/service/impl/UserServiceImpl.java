package cn.com.testol.service.impl;

import cn.com.testol.DTO.UserClassesDTO;
import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.UserDao;
import cn.com.testol.dao.UserPasswordDao;
import cn.com.testol.entity.Classes;
import cn.com.testol.entity.User;
import cn.com.testol.entity.UserPassword;
import cn.com.testol.service.UserService;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional  //事务的注解
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private UserPasswordDao userPasswordDao;


    @Override
    public User login(String name, String password) {
        User user=userDao.selectByEmail(name);
        if (user==null){
            user=userDao.selectByPhone(name);
        }
        if (user==null){
            return null;
        }
        System.out.println(user);
        User isUser=userDao.loginByEmail(user.getUserId(),name,password);
        if(isUser==null){
            isUser=userDao.loginByPhone(user.getUserId(),name,password);
        }else{
            return isUser;
        }
        return isUser;

    }

    @Override
    public User getUserById(int u_id) {
        User user=userDao.selectByPrimaryKey(u_id);
        //处理班级编号
        String id=user.getUserId()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return user;
    }





    @Override
    public Msg queryUserByC_id(int c_id, int userId) {
        Classes classes = classesDao.selectByPrimaryKey(c_id);
        List<UserClassesDTO> userList=userDao.selectByC_id(c_id);
        for(UserClassesDTO u_c:userList){
            switch (u_c.getPosition()){
                case "creator": u_c.setPosition("创建者");break;
                case "admin": u_c.setPosition("管理员");break;
                case "student": u_c.setPosition("学生");break;
            }
        }

        if(userList.size() >= 0){
            return ResultUtil.success(userList);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @Override
    public Msg addUser(User user, String password) {
        try{
            if(userDao.selectByEmail(user.getEmail())!=null){
                return ResultUtil.error(1001,"该邮箱地址已被使用");
            }
            if(userDao.selectByPhone(user.getPhone())!=null && !user.getPhone().equals("")){
                return ResultUtil.error(1002,"该手机号码已被使用");
            }

            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setRole("student");
            userDao.insert(user);

            userPasswordDao.insert(new UserPassword(user.getUserId() ,password));
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public Msg getRole(Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        if (user.getRole().equals("student")){

        }
        if (user.getRole().equals("teacher")){

        }
        return null;
    }

    @Override
    public Msg changeRole(Integer userId) {
        try{
            User user = userDao.selectByPrimaryKey(userId);
            if(user.getRole().equals("student")){
                user.setRole("teacher");
            }else {
                user.setRole("student");
            }
            userDao.updateByPrimaryKeySelective(user);
            String token= JwtUtil.sign(user.getUserName(),user.getUserId()+"",user.getRole());
            if(token!=null){
                HashMap<String,Object> hm = new HashMap<String,Object>();
                hm.put("token",token);
                hm.put("status",user.getRole());
                hm.put("name",user.getUserName());
                return ResultUtil.success(hm);
            }
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.error(100,"请求失败",e.toString());
        }
    }

    @Override
    public int updateUser(User user) {
        user.setUpdateDate(new Date());
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteByPrimaryKey(id);
    }
}
