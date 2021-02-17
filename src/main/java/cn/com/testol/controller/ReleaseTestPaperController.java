package cn.com.testol.controller;

import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.pojo.TestPaper_classes;
import cn.com.testol.service.ReleaseTestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReleaseTestPaperController {
    @Autowired
    private ReleaseTestPaperService releaseTestPaperService;

    @RequestMapping("/getClassesByTp_id")
    public Msg getClassesByTp_id(int tp_id){
        List<TestPaper_classes> testPaperClassesList=releaseTestPaperService.getClassesByTp_id(tp_id);
        if(testPaperClassesList != null){
            return ResultUtil.success(testPaperClassesList);
        }else {
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping("/getTestpapertByC_id")
    public Msg getTestpapertByC_id(String token,int c_id){
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        List<TestPaper_classes> testPaperClassesList=releaseTestPaperService.getTestpapertByC_id(c_id,u_id);
        if(testPaperClassesList!=null){
            return ResultUtil.success(testPaperClassesList);
        }else {
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping(value = "/releaseTest", method = RequestMethod.POST)
    public Msg releaseTest(String token,int tp_id, int[] c_id,String release_time,String start_date,String deadline){

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }


        int result=releaseTestPaperService.releaseTest(tp_id, c_id, release_time, start_date, deadline);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping(value = "/updateReleaseTest", method = RequestMethod.POST)
    public Msg updateReleaseTest(String token,int tp_id,int c_id,String release_time,String start_date,String deadline){
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        int result=releaseTestPaperService.updateRecord(tp_id, c_id,release_time, start_date, deadline);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    @RequestMapping(value = "/cancelRelease", method = RequestMethod.POST)
    public Msg cancelRelease(String token,int tp_id,int c_id){
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
