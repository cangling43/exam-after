package cn.com.testol.controller;

import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.pojo.User;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/getUserById")
    public Msg getUserById( String token){
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        User user = userService.getUserById(u_id);

        if(user != null){
            return ResultUtil.success(user);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }




    @RequestMapping(value="/login", method= RequestMethod.POST )
    public Msg login(@RequestParam String name, String password){
//        System.out.println(name+password);
        User user = userService.login(name,password);

        if (user!=null){
            String token= JwtUtil.sign(user.getName(),user.getU_id()+"",user.getStatus());
            if(token!=null){
                HashMap<String,Object> hm = new HashMap<String,Object>();
                hm.put("token",token);
                hm.put("status",user.getStatus());
                hm.put("name",user.getName());
                return ResultUtil.success(hm);
            }
        }
        return ResultUtil.error(10001,"用户名或密码错误");


    }

    @RequestMapping(value = "/register", method= RequestMethod.POST )
    public Msg register( String name,String email,String phone,String password, String date){
        HashMap<String,Object> hm = new HashMap<String,Object>();
        System.out.println(name);
        if(userService.getUserByEmail(email)!=null){
            return ResultUtil.error(1001,"该邮箱地址已被使用");
        }
        if(phone.equals("")){
            System.out.println("00"+phone);
        }
        if(userService.getUserByPhone(phone)!=null && !phone.equals("")){
            return ResultUtil.error(1002,"该手机号码已被使用");
        }

        User user=new User();
        user.setName(name);
        user.setCreate_date(date);
        user.setEmail(email);
        user.setPhone(phone);

        int result=userService.addUser(user,password);
//        int result=1;

        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }




    @RequestMapping("/queryUserByC_id")
    public Msg queryUserByC_id(String token,int c_id){
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        List<User_classes> userList=userService.queryUserByC_id(c_id);
        Boolean inClasses=false;
        for(User_classes u_c:userList){
            User u = u_c.getUser();
            if(u.getU_id()==u_id){
                inClasses=true;
                break;
            }
        }
        if (!inClasses) {
            return ResultUtil.error(2004,"您不是该班级的学生");
        }

        if(userList.size()>0){
            return ResultUtil.success(userList);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }


    @RequestMapping(value = "/updateUser", method= RequestMethod.POST )
    public Msg updateUser(int id, String name, String sex, String phone, String email, String photo, String status, String work){
        int result=userService.updateUser(id,name,sex,phone,email,photo,status,work);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"修改失败");
        }
    }

    @RequestMapping(value = "/deleteUser", method= RequestMethod.POST )
    public String deleteUser(int id){
        int result=userService.deleteUser(id);
        if(result==1){
            return "注销成功";
        }else {
            return "注销失败";
        }
    }
}
