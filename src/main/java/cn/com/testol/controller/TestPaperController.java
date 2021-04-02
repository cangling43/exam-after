package cn.com.testol.controller;

import cn.com.testol.DTO.ExamTopicTchDTO;
import cn.com.testol.DTO.StuSubmitExamDTO;
import cn.com.testol.service.ExamService;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.service.MarkTestPaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestPaperController {
    @Autowired
    private ExamService examService;
    @Autowired
    private MarkTestPaperService markTestPaperService;

    @ApiOperation(value = "获取用户创建的试卷")
    @GetMapping("/getTestPaperByU_id")
    public Msg getTestPaperByU_id(HttpServletRequest request){
        String token =  request.getHeader("token");

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        return examService.selectByCreatorId(u_id);
    }

//    @ApiOperation(value = "获取用户创建的试卷")
//    @GetMapping("/getTestPaperByU_id")
//    public Msg getTestPaperByExamId(HttpServletRequest request,Integer examId){
//        String token =  request.getHeader("token");
//
//        //获取token中的id
//        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
//        return examService.selectByCreatorId(u_id);
//    }

    @ApiOperation(value = "获取班级下的试卷列表")
    @GetMapping("/getExamByClasses")
    public Msg getExamByClassesId(HttpServletRequest request,Integer classesId){
        String token =  request.getHeader("token");

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        return examService.selectByClassesId(classesId, u_id);
    }

    //通过试卷id获取试卷信息(教师角色)
    @ApiOperation(value = "获取试卷信息(教师角色)")
    @GetMapping("/getTestPaperByTp_id")
    public Msg getTestPaperByTp_id(HttpServletRequest request,int examId){
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }
        int userId = Integer.parseInt(JwtUtil.getUserId(token));
        return examService.tchSelectByPrimaryKey(userId,examId);
    }

    //通过试卷id,班级id获取试卷信息(学生角色)
    @ApiOperation(value = "获取试卷信息(学生角色)")
    @GetMapping("/getTestPaper")
    public Msg getTestPaper(HttpServletRequest request,int examId ,int classesId){
        String token =  request.getHeader("token");
        int userId=Integer.parseInt(JwtUtil.getUserId(token));
        return examService.stuSelectByPrimaryKey(userId,classesId,examId);
    }

    //创建试卷
    @ApiOperation(value = "创建试卷")
    @PostMapping(value = "/createTestPaper")
    public Msg createTestPaper(@RequestBody ExamTopicTchDTO examTopicTchDTO,HttpServletRequest request){

        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        int userId=Integer.parseInt(JwtUtil.getUserId(token));
        return examService.insert(examTopicTchDTO,userId);
    }

    //更改试卷
    @ApiOperation(value = "更改试卷")
    @PostMapping(value = "/updateTestPaper" )
    public Msg updateTestPaper(@RequestBody ExamTopicTchDTO examTopicTchDTO,HttpServletRequest request){
        //获取token
        String token =  request.getHeader("token");

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return  examService.updateByPrimaryKeySelective(examTopicTchDTO,u_id);

    }



    //提交试卷(学生角色)
    @ApiOperation(value = "提交试卷(学生角色)")
    @PostMapping(value = "/submitTestPaper" )
    public Msg submitTestPaper(@RequestBody StuSubmitExamDTO stuSubmitExamDTO, HttpServletRequest request){
        System.out.println(stuSubmitExamDTO);
        String token =  request.getHeader("token");
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        return markTestPaperService.submitTestPaper(stuSubmitExamDTO,u_id);
    }


}
