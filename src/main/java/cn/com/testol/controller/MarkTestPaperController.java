package cn.com.testol.controller;

import cn.com.testol.dao.TopicMapper;
import cn.com.testol.dao.User_ClassesMapper;
import cn.com.testol.pojo.TestPaper;
import cn.com.testol.pojo.TestPaper_classes;
import cn.com.testol.pojo.Topic;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.service.MarkTestPaperService;
import cn.com.testol.service.TestPaperService;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarkTestPaperController {
    @Autowired
    private MarkTestPaperService markTestPaperService;
    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private User_ClassesMapper user_classesMapper;

    //通过试卷id,班级id获取试卷信息(学生角色)
    @RequestMapping("/getTestPaperMark")
    public Msg getTestPaper(String token,int tp_id ,int c_id,int u_id){

        //获取token中的id
        int teacher_id=Integer.parseInt(JwtUtil.getUserId(token));

        //判断是否为该试卷的创建者
        TestPaper testPaper = testPaperService.getTestPaper(tp_id);
        if(testPaper.getCreator_id() != teacher_id){
            return ResultUtil.error(2003,"您不是该班级管理员");
        }

        //判断是否是该班级的学生
        User_classes inClasses=user_classesMapper.selectRecord(u_id,c_id);
        if(inClasses == null){
            return ResultUtil.error(3003,"不是该班级的学生");
        }

        TestPaper_classes testPaperClasses=testPaperService.getTestPaperByStu(tp_id,c_id,u_id);


        if(testPaperClasses != null){
            return ResultUtil.success(testPaperClasses);
        }else{
            return ResultUtil.error(100,"请求失败");
        }
    }
}
