package cn.com.testol.controller;

import cn.com.testol.DTO.UserGradeDTO;
import cn.com.testol.service.MarkExamService;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MarkExamController {
    @Autowired
    private MarkExamService markExamService;


    @ApiOperation(value = "获取班级考试下")
    @GetMapping("/getUserGradeList")
    public Msg getUserGradeList(HttpServletRequest request,@RequestParam Integer classesId,@RequestParam Integer examId){
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        int teacher_id=Integer.parseInt(JwtUtil.getUserId(token));

        return markExamService.selectByClassesId(classesId,examId,teacher_id);

    }

    @ApiOperation(value = "获取学生答卷")
    @GetMapping("/getStuExamInfo")
    public Msg getStuExamInfo(HttpServletRequest request,@RequestParam Integer classesId,@RequestParam Integer examId,@RequestParam Integer userId){
        String token =  request.getHeader("token");
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }
        int teacher_id=Integer.parseInt(JwtUtil.getUserId(token));


        return markExamService.selestStuExamInfo(classesId,examId,userId);
    }

    @ApiOperation(value = "批改试卷(教师角色)")
    @PutMapping("/tchMarkExam")
    public Msg tchMarkExam(HttpServletRequest request, @RequestBody UserGradeDTO userGradeDTO){
        String token =  request.getHeader("token");
        //获取token中的id
        int teacher_id=Integer.parseInt(JwtUtil.getUserId(token));

        return markExamService.tchMarkExam(userGradeDTO,teacher_id);
    }



}
