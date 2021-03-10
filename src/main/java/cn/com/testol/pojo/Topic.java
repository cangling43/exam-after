package cn.com.testol.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Topic {
    @Id
    private int t_id;//题目id
    private int u_id;//用户id
    private int subject_id;//科目类型id
    private String question;//题目问题
    private String choice;//选项
    private String photo;//图片
    private int topic_type;//题目类型  0:单选题 1:多选题 2:判断题 3:填空题 4:简答题
    private String correct_answer;//正确答案

    @Column(name="t_score")
    private double score;//分值
    private String difficulty;//难度  简单,中等(默认),困难
    private String analysis;//答案解析
//    private String user_answer;



}
