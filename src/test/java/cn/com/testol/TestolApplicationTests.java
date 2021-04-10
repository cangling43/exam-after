package cn.com.testol;

import cn.com.testol.dao.ClassesDao;
import cn.com.testol.dao.ExamDao;
import cn.com.testol.dao.UserDao;
import cn.com.testol.service.ClassesService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestolApplicationTests {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExamDao examDao;

    @Test
    void contextLoads() {
        System.out.println(examDao.selectByCreatorId(1,""));
//        System.out.println(userDao.selectByC_id(1));
    }

}
