package cn.com.testol.pojo;

import java.util.List;

public class SubmitTestPaper {
    private String token;
    private int c_id;
    private List<User_topic> userTopics;
    private String date;
    private int time;//(秒)

    @Override
    public String toString() {
        return "SubmitTestPaper{" +
                "token='" + token + '\'' +
                ", c_id=" + c_id +
                ", userTopics=" + userTopics +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<User_topic> getUserTopics() {
        return userTopics;
    }

    public void setUserTopics(List<User_topic> userTopics) {
        this.userTopics = userTopics;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
