package cn.com.testol.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SubmitTestPaper {
    private String token;
    private int c_id;
    private List<User_topic> userTopics;
    private String date;
    private int time;//(秒)


}
