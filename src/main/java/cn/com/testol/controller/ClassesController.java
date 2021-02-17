package cn.com.testol.controller;


import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.pojo.Classes;
import cn.com.testol.utils.Msg;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin()
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    private List<Map<String, Object>> data;

    /*
    * 通过用户id查找班级
    * */
    @RequestMapping("/queryClassesByU_id")
    public Msg queryClassesByU_id(String token){
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        List<User_classes> userClassesList=classesService.queryClassesByU_id(u_id);
        return ResultUtil.success(userClassesList);
    }

    /*
     * 通过班级id查找班级
     * */
    @RequestMapping("/queryClassesByC_id")
    public Msg queryClassesByC_id(int c_id){
        User_classes userClasses=classesService.queryClassesByC_id(c_id);

        if(userClasses == null){
            return ResultUtil.error(2001,"该班级不存在");
        }

        return ResultUtil.success(userClasses);
    }


    @RequestMapping(value = "/joinClasses", method= RequestMethod.POST )
    public Msg joinClasses(String token,int c_id ,String date) {


        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        Classes classes=classesService.queryClassesByC_id(c_id).getClasses();
        if(classes==null){
            return ResultUtil.error(2001,"该班级不存在");
        }

        int result=classesService.joinClasses(u_id,c_id,"student",date);
        if(result==-1){
            return ResultUtil.error(2002,"已加入该班级");
        }
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping(value = "/outClasses", method= RequestMethod.POST )
    public Msg outClasses(String token,int c_id){


        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        int result=classesService.outClasses(u_id,c_id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping(value = "/outClassesByteacher", method= RequestMethod.POST )
    public Msg outClassesByteacher(String token,int u_id,int c_id){

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int t_id=Integer.parseInt(JwtUtil.getUserId(token));
        Classes classes=classesService.queryClassesByC_id(c_id).getClasses();
        if(t_id != classes.getCreator_id()){
            return ResultUtil.error(400,"您不是该班级管理员");
        }

        int result=classesService.outClasses(u_id,c_id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    /*
    添加班级 createClasses
        班级名称          name
        班级简介          introduction
        创建人id          creator_id
        班级允许加入方式    jionWay
         */
    @RequestMapping(value = "/createClasses" , method= RequestMethod.POST)
    public Msg createClasses(String name, String token, String joinWay ,String date){

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        Classes classes=new Classes();
        classes.setName(name);
        classes.setIntroduction("");
        classes.setCreator_id(u_id);
        classes.setJoinWay(joinWay);
        classes.setCreate_date(date);
        classes.setPeople_num(1);
        int result= classesService.createClasses(classes , u_id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }

    }


    /*
   修改班级信息 createClasses
       班级名称          name
       班级简介          introduction
       班级允许加入方式    jionWay
        */
    @RequestMapping(value = "/updateClasses" , method= RequestMethod.POST)
    public Msg updateClasses(String token,String name,String introduction,String joinWay,int people_num,int c_id){

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }
        //获取token中的id
        int t_id=Integer.parseInt(JwtUtil.getUserId(token));
        Classes classes=classesService.queryClassesByC_id(c_id).getClasses();
        if(t_id != classes.getCreator_id()){
            return ResultUtil.error(400,"您不是该班级管理员");
        }

        int result= classesService.updateClasses(name,introduction,joinWay ,people_num,c_id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    /*
   删除班级 createClasses
       班级id      id
        */
    @RequestMapping(value = "/deleteClasses" , method= RequestMethod.POST)
    public Msg deleteClasses(String token,int id){

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        int result= classesService.deleteClasses(id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }
}
