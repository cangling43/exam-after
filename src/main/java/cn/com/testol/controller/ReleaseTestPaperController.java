package cn.com.testol.controller;

import cn.com.testol.DTO.ReleasExamDTO;
import cn.com.testol.dao.ExamClassesDao;
import cn.com.testol.entity.ExamClasses;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.service.ReleaseTestPaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class ReleaseTestPaperController {
    @Autowired
    private ReleaseTestPaperService releaseTestPaperService;
    @Autowired
    private ExamClassesDao examClassesDao;

    @ApiOperation(value = "获取发布考试信息")
    @GetMapping(value = "/getReleaseInfo")
    public Msg getReleaseInfo(HttpServletRequest request,Integer classesId,Integer examId){
        String token =  request.getHeader("token");

        return releaseTestPaperService.getReleaseInfo(classesId,examId);
    }

    @ApiOperation(value = "发布考试")
    @PostMapping(value = "/releaseTest")
    public Msg releaseTest(HttpServletRequest request, @RequestBody ReleasExamDTO releasExamDTO){
        System.out.println(releasExamDTO);
        String token =  request.getHeader("token");

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }


        return releaseTestPaperService.releaseTest(releasExamDTO);
    }

    @ApiOperation(value = "修改试卷发布信息")
    @PostMapping(value = "/updateReleaseTest",consumes = "application/json")
    public Msg updateReleaseTest(HttpServletRequest request, @RequestBody ExamClasses examClasses){

        String token =  request.getHeader("token");

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        examClasses.setReleaseTime(new Date());
        int result=examClassesDao.updateRecord(examClasses);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @ApiOperation(value = "取消试卷发布")
    @DeleteMapping(value = "/cancelRelease")
    public Msg cancelRelease(HttpServletRequest request,int tp_id,int c_id){
        String token =  request.getHeader("token");

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        int result=releaseTestPaperService.deleteRecord(tp_id, c_id);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }
}
