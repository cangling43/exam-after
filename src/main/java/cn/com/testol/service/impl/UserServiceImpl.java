package cn.com.testol.service.impl;

import cn.com.testol.pojo.User;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.service.UserService;
import cn.com.testol.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional  //事务的注解
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(String name, String password) {
        User user=userMapper.getUserByEmail(name);
        if (user==null){
            user=userMapper.getUserByPhone(name);
        }
        if (user==null){
            return null;
        }
        User isUser=userMapper.loginByEmail(user.getU_id(),name,password);
        if(isUser==null){
            isUser=userMapper.loginByPhone(user.getU_id(),name,password);
        }else{
            return isUser;
        }
        return isUser;

    }

    @Override
    public User getUserById(int u_id) {
        User user=userMapper.getUserById(u_id);
        //处理班级编号
        String id=user.getU_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        user.setNumber(id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public List<User_classes> queryUserByC_id(int c_id) {
        List<User_classes> userList=userMapper.queryUserByC_id(c_id);
        for(User_classes u_c:userList){
            //处理班级编号
//            String id=u.getU_id()+"";
//            while (id.length()<5){
//                id="0"+id;
//            }
//            u.setNumber(id);

            switch (u_c.getStatus()){
                case "creator": u_c.setStatus("创建者");break;
                case "admin": u_c.setStatus("管理员");break;
                case "student": u_c.setStatus("学生");break;
            }
        }
        return userList;
    }

    @Override
    public int addUser(User user,String password) {
        int result=0;
        int id=-1;
        try{
            userMapper.addUserInfo(user);
            id=user.getU_id();
            if(id==-1){
//                System.out.println("回滚");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }else {
                result=userMapper.addUserPassword(id,password);
            }
        }catch (Exception e){
            System.out.println(e);
            //强制手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Override
    public int updateUser(int id, String name, String sex, String phone, String email, String photo, String status, String work) {
        return userMapper.updateUser(id,name,sex,phone,email,photo,status,work);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}
