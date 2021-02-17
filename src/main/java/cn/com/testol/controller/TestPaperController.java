package cn.com.testol.controller;

import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import cn.com.testol.dao.User_ClassesMapper;
import cn.com.testol.pojo.*;
import cn.com.testol.service.MarkTestPaperService;
import cn.com.testol.service.TestPaperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestPaperController {
    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private User_ClassesMapper user_classesMapper;
    @Autowired
    private MarkTestPaperService markTestPaperService;

    @RequestMapping("/getTestPaperByU_id")
    public Msg getTestPaperByU_id(String token){


        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        List<TestPaper> testPaperList=testPaperService.getTestPaperByU_id(u_id);
        if(testPaperList!=null){
            return ResultUtil.success(testPaperList);
        }else {
            return ResultUtil.error(100,"请求失败");
        }

    }

    //通过试卷id获取试卷信息(教师角色)
    @RequestMapping("/getTestPaperByTp_id")
    public Msg getTestPaperByTp_id(String token,int tp_id){
        //获取token

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));


        //判断是否为该试卷的创建者
        TestPaper testPaper=testPaperService.getTestPaperByTp_id(tp_id);
        if(testPaper.getCreator_id() != u_id){
            return ResultUtil.error(3001,"您没有权利查看该试卷");
        }

        System.out.println(testPaper.toString());
        if(testPaper != null){
            return ResultUtil.success(testPaper);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    //通过试卷id,班级id获取试卷信息(学生角色)
    @RequestMapping("/getTestPaper")
    public Msg getTestPaper(String token,int tp_id ,int c_id){
        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        //判断是否是该班级的学生
        User_classes inClasses=user_classesMapper.selectRecord(u_id,c_id);
        if(inClasses == null){
            return ResultUtil.error(3003,"您不是该班级的学生");
        }

        TestPaper_classes testPaperClasses=testPaperService.getTestPaperByStu(tp_id,c_id,u_id);
        //去除答案和答案解析
        List<Topic> topicList=testPaperClasses.getTestPaper().getTopic();
        for(Topic t:topicList){
            t.setCorrect_answer("null");
            t.setAnalysis("null");
        }

        if(testPaperClasses != null){
            return ResultUtil.success(testPaperClasses);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    //创建试卷
    @RequestMapping(value = "/createTestPaper" ,method= RequestMethod.POST)
    public Msg createTestPaper(@RequestBody RequestTestPaper requestTestPaper){
        String token=requestTestPaper.getToken();
        if(!StringUtils.isBlank(token)){
            boolean result = JwtUtil.verify(token);
            if (!result) {
                return ResultUtil.error(300,"token验证失败,请先登录");
            }
        }
        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));
        TestPaper testPaper=requestTestPaper.getTestPaper();
        //设置试卷创建者id
        testPaper.setCreator_id(u_id);

        //设置题目创建者id
        List<Topic> topicList=requestTestPaper.getTestPaper().getTopic();
        for(Topic t: topicList){
            t.setU_id(u_id);
        }
//        System.out.println(requestTestPaper.getToken());
        System.out.println(requestTestPaper.getTestPaper().toString());
//        System.out.println(requestTestPaper.getTestPaper().getTopic().toString());

//        return ResultUtil.success(requestTestPaper);
        int result=testPaperService.createTestPaper(testPaper,testPaper.getTopic());
        if(result>0){
            return ResultUtil.success(result);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    //更改试卷
    @RequestMapping(value = "/updateTestPaper" ,method= RequestMethod.POST)
    public Msg updateTestPaper(@RequestBody RequestTestPaper requestTestPaper){
        //获取token
        String token=requestTestPaper.getToken();
        if(!StringUtils.isBlank(token)){
            boolean result = JwtUtil.verify(token);
            if (!result) {
                return ResultUtil.error(300,"token验证失败,请先登录");
            }
        }

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        TestPaper testPaper=requestTestPaper.getTestPaper();

        //判断是否为该试卷的创建者
        TestPaper testPaperDate=testPaperService.getTestPaperByTp_id(testPaper.getTp_id());
        if(testPaperDate.getCreator_id() != u_id){
            return ResultUtil.error(3002,"您没有权利更改该试卷");
        }

//        System.out.println(requestTestPaper.getTestPaper().toString());

//        return ResultUtil.success(requestTestPaper);
        int result=testPaperService.updateTestPaper(testPaper,testPaper.getTopic());
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }



    //提交试卷(学生角色)
    @RequestMapping(value = "/submitTestPaper" ,method= RequestMethod.POST)
    public Msg submitTestPaper(@RequestBody SubmitTestPaper submitTestPaper){
//        System.out.println(submitTestPaper.getUserTopics().toString());
        String token = submitTestPaper.getToken();
        if(!StringUtils.isBlank(token)){
            boolean result = JwtUtil.verify(token);
            if (!result) {
                return ResultUtil.error(300,"token验证失败,请先登录");
            }
        }

        System.out.println(submitTestPaper.toString());
//        return ResultUtil.success();

        int result=markTestPaperService.submitTestPaper(submitTestPaper);
        if(result>0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }

    //批改试卷(教师角色)
    @RequestMapping(value = "/markTestPaper" ,method= RequestMethod.POST)
    public Msg markTestPaper(@RequestBody RequestTestPaper requestTestPaper){
        //获取token
        String token=requestTestPaper.getToken();
        if(!StringUtils.isBlank(token)){
            boolean result = JwtUtil.verify(token);
            if (!result) {
                return ResultUtil.error(300,"token验证失败,请先登录");
            }
        }

        if(!JwtUtil.getUserStatus(token).equals("teacher")){
            return ResultUtil.error(400,"用户身份不正确");
        }

        //获取token中的id
        int u_id=Integer.parseInt(JwtUtil.getUserId(token));

        TestPaper testPaper=requestTestPaper.getTestPaper();

        

        return ResultUtil.success();

    }
}
