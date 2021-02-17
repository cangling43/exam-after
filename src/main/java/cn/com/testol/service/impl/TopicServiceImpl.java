package cn.com.testol.service.impl;

import cn.com.testol.dao.TopicMapper;
import cn.com.testol.pojo.Topic;
import cn.com.testol.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  //事务的注解
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int createTopic(Topic topic) {
        return topicMapper.createTopic(topic);
    }
}
