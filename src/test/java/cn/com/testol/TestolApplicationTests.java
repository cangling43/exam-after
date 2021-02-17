package cn.com.testol;

import cn.com.testol.dao.TestPaperMapper;
import cn.com.testol.pojo.TestPaper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestolApplicationTests {
    @Autowired

    @Test
    void contextLoads() {
        System.out.println("test");
    }

}
