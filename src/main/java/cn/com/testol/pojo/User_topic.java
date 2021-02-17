package cn.com.testol.pojo;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class User_topic {
    @Id
    private int ut_id;
    private int u_id;//用户id
    private int t_id;//题目id
    private int tp_id;//试卷id
    private int c_id;//班级id
    private String user_answer;//用户的答案
    @Column(name="ut_score")
    private double score;//得分
    @Column(name="ut_status")
    private int status;//状态 0:待批改 1:批改完成
//    private String correct_answer;//正确答案


    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getUt_id() {
        return ut_id;
    }

    public void setUt_id(int ut_id) {
        this.ut_id = ut_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
