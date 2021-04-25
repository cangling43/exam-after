package cn.com.testol.controller;

import cn.com.testol.DTO.LoginDTO;
import cn.com.testol.DTO.RegisterDTO;
import cn.com.testol.dao.UserDao;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Page;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.entity.User;
import cn.com.testol.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getUserById")
    public Msg getUserById(HttpServletRequest request){
        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        User user = userDao.selectByPrimaryKey(u_id);

        if(user != null){
            return ResultUtil.success(user);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping(value="/login")
    public Msg login(@RequestBody LoginDTO loginDTO){
//        System.out.println(name+password);
        User user = userService.login(loginDTO.getName(),loginDTO.getPassword());

        if (user!=null){
            String token= JwtUtil.sign(user.getUserName(),user.getUserId()+"",user.getRole());
            if(token!=null){
                HashMap<String,Object> hm = new HashMap<String,Object>();
                hm.put("token",token);
                hm.put("status",user.getRole());
                hm.put("name",user.getUserName());
                return ResultUtil.success(hm);
            }
        }
        return ResultUtil.error(10001,"用户名或密码错误");


    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public Msg register(@RequestBody RegisterDTO registerDTO) throws ParseException {
        User user=new User();
        BeanUtils.copyProperties(registerDTO,user);

        return userService.addUser(user,registerDTO.getPassword());
    }



    @ApiOperation(value = "获取班级下的用户列表")
    @GetMapping("/queryUserByC_id")
    public Msg queryUserByC_id(HttpServletRequest request,@RequestParam int c_id,@RequestParam int pageSize,@RequestParam int currentPage){
        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        Msg result=userService.queryUserByC_id(c_id, u_id);
        Page<List> page = new Page(pageSize,currentPage);
        page.build((List) result.getData());
        return ResultUtil.success(page);
    }


    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/updateUser")
    public Msg updateUser(@RequestBody User user){
        int result=userService.updateUser(user);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"修改失败");
        }
    }

    @ApiOperation(value = "获取用户权限")
    @GetMapping(value = "getRole")
    public Msg getRole(HttpServletRequest request){
        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        return userService.getRole(u_id);
    }

    @ApiOperation(value = "切换用户角色")
    @PutMapping(value = "changeRole")
    public Msg changeRole(HttpServletRequest request){
        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return userService.changeRole(u_id);
    }

    @DeleteMapping(value = "/deleteUser")
    public String deleteUser(int id){
        int result=userService.deleteUser(id);
        if(result==1){
            return "注销成功";
        }else {
            return "注销失败";
        }
    }
}
