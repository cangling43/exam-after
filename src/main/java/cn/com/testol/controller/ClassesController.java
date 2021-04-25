package cn.com.testol.controller;


import cn.com.testol.dao.ClassesDao;
import cn.com.testol.entity.Classes;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Page;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.service.ClassesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@RestController
//@CrossOrigin()
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ClassesDao classesDao;

    /*
     * 查找班级
     * */
    @ApiOperation(value = "查找班级")
    @GetMapping("/queryClasses")
    public Msg queryClasses(@RequestParam Integer classesId){
        return classesService.queryClassesByC_id(classesId);
    }

    /*
     * 查找班级列表
     * */
    @ApiOperation(value = "查找班级列表")
    @GetMapping("/queryClassesList")
    public Msg queryClassesList(HttpServletRequest request,@RequestParam(required = false) Integer examId,
                                @RequestParam int pageSize,@RequestParam int currentPage,
                                @RequestParam(required = false) String keyword){
        if (examId != null){
            return classesService.queryClassesByExamId(examId);
        }

        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        if(keyword == null){
            keyword = "";
        }
        String role = JwtUtil.getUserStatus(token);
        Msg result = classesService.queryClassesByU_id(u_id,role,keyword);
        Page<List> page = new Page(pageSize,currentPage);
        page.build((List) result.getData());
        return ResultUtil.success(page);
}

    @ApiOperation(value = "加入班级")
    @GetMapping(value = "/joinClasses")
    public Msg joinClasses(HttpServletRequest request,@RequestParam int c_id ) {

        String token =  request.getHeader("token");
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return classesService.joinClasses(u_id,c_id,"student",new Date());

    }

    @ApiOperation(value = "退出班级")
    @DeleteMapping(value = "/outClasses")
    public Msg outClasses(HttpServletRequest request,@RequestParam int c_id){
        String token =  request.getHeader("token");

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return classesService.outClasses(u_id,c_id);

    }

    @ApiOperation(value = "踢出班级（老师）")
    @DeleteMapping(value = "/outClassesByTeacher")
    public Msg outClassesByteacher(HttpServletRequest request,@RequestParam int u_id,@RequestParam int c_id){
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int t_id=Integer.parseInt(JwtUtil.getUserId(token));
        Classes classes=classesDao.selectByPrimaryKey(c_id);
        if(t_id != classes.getCreatorId()){
            return ResultUtil.error(400,"您不是该班级管理员");
        }

        return classesService.outClasses(u_id,c_id);
    }

    /*
    添加班级 createClasses
        班级名称          name
        班级简介          introduction
        创建人id          creator_id
        班级允许加入方式    jionWay
         */
    @ApiOperation(value = "创建班级")
    @PostMapping(value = "/createClasses")
    public Msg createClasses( HttpServletRequest request,@RequestBody Classes classes) throws ParseException {
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return classesService.createClasses(classes,u_id);

    }


    /*
   修改班级信息 createClasses
       班级名称          name
       班级简介          introduction
       班级允许加入方式    jionWay
        */
    @ApiOperation(value = "修改班级信息")
    @PutMapping(value = "/updateClasses" )
    public Msg updateClasses(HttpServletRequest request,@RequestBody Classes classes){
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }
        //获取token中的id
        Integer userId=Integer.parseInt(JwtUtil.getUserId(token));

        return classesService.updateClasses(classes,userId);
    }


    @ApiOperation(value = "班级列表-模糊搜索")
    @GetMapping(value = "/classes/fuzzyQuery" )
    public Msg fuzzyQuery(@RequestParam String keyword){

        return classesService.fuzzyQuery(keyword);
    }

    /*
   删除班级 createClasses
       班级id      id
        */
    @ApiOperation(value = "删除班级")
    @DeleteMapping(value = "/deleteClasses" )
    public Msg deleteClasses(HttpServletRequest request,@RequestParam int id){
        String token =  request.getHeader("token");
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
