package cn.com.testol.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Data
public class TestPaper {
    @Id
    private int tp_id;
    private String number;//试卷编号
    @Column(name = "tp_name")
    private String name;//试卷名称
    private int creator_id;//创建人id
    private int time;//答题时间(分钟)
    private String create_date;//创建时间
    private int subject_id;//试卷类型id
    private int topic_num;//题目数量
    private Float total_score;//总分数
    private Float pass_mark;//及格分数
    private int permit_copy;//是否允许复制  0:不允许  1:允许(默认)
    private int disrupt_order;//是否打乱题目顺序 0:不打乱(默认) 1:打乱顺序
    private int repeat_test;//允许考生考试次数 默认1
    private int auto_mack;//是否自动评分 0:否   1:是(默认)

    private String subject;
    @Transient
    private List<Topic> topic;
    @Transient
    private UserGrade userGrade;
    @Transient
    private List<User_topic> userTopic;

    public String getNumber() {
        String id=getTp_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return "T"+id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
