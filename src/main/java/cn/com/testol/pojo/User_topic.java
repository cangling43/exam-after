package cn.com.testol.pojo;

//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
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



}
