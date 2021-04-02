package cn.com.testol.service.impl;

import cn.com.testol.entity.Topic;
import cn.com.testol.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  //事务的注解
public class TopicServiceImpl implements TopicService {


    @Override
    public int createTopic(Topic topic) {
        return 0;
    }
}
