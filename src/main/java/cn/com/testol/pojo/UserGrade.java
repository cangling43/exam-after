package cn.com.testol.pojo;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

import javax.persistence.*;


@Entity(name = "user_grade")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class UserGrade {
    @Id
    private int id;//id
    private int u_id;//用户id
    private int c_id;//班级id
    private int tp_id;//试卷id
    private String userName;
    private String userNumber;
    private double grade;//成绩
    @Column(name = "grade_auto")
    private double gradeAuto;//机器自动评分的成绩
    @Column(name = "answer_date")
    private String date;//完成日期
    @Column(name = "answer_time")
    private int answerTime;//完成时间
    private int status;//状态 0:待批改 1:批改完成

    @Override
    public String toString() {
        return "UserGrade{" +
                "id=" + id +
                ", u_id=" + u_id +
                ", c_id=" + c_id +
                ", tp_id=" + tp_id +
                ", userName='" + userName + '\'' +
                ", grade=" + grade +
                ", gradeAuto=" + gradeAuto +
                ", date='" + date + '\'' +
                ", answerTime=" + answerTime +
                ", status=" + status +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        String id=getU_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return "U"+id;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getGradeAuto() {
        return gradeAuto;
    }

    public void setGradeAuto(double gradeAuto) {
        this.gradeAuto = gradeAuto;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}



