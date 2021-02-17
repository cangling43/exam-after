package cn.com.testol.controller;

import cn.com.testol.dao.ClassesMapper;
import cn.com.testol.dao.TopicMapper;
import cn.com.testol.pojo.Classes;
import cn.com.testol.pojo.Topic;
import cn.com.testol.pojo.User_classes;
import cn.com.testol.utils.Msg;
import cn.com.testol.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private ClassesMapper classesMapper;

    @RequestMapping("/test")
    public Msg test(int num){
        Topic result = topicMapper.getTopicByt_id(num);
//        User_classes result=classesMapper.queryClassesByC_id(num);
        return ResultUtil.success(result);
    }
}
