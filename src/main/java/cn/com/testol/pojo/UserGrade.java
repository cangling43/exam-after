package cn.com.testol.pojo;

//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

import javax.persistence.*;


@Entity(name = "user_grade")
@Data
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
    private int status;//状态 0:未完成 0:待批改 1:批改完成



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


}



