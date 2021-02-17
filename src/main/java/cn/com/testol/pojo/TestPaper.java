package cn.com.testol.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
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


    @Override
    public String toString() {
        return "TestPaper{" +
                "tp_id=" + tp_id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", creator_id=" + creator_id +
                ", time=" + time +
                ", create_date='" + create_date + '\'' +
                ", subject_id=" + subject_id +
                ", topic_num=" + topic_num +
                ", total_score=" + total_score +
                ", pass_mark=" + pass_mark +
                ", permit_copy=" + permit_copy +
                ", disrupt_order=" + disrupt_order +
                ", repeat_test=" + repeat_test +
                ", auto_mack=" + auto_mack +
                ", subject=" + subject +
                ", topic=" + topic +
                ", userGrade=" + userGrade +
                ", userTopic=" + userTopic +
                '}';
    }

    public List<User_topic> getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(List<User_topic> userTopic) {
        this.userTopic = userTopic;
    }

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


    public UserGrade getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(UserGrade userGrade) {
        this.userGrade = userGrade;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public Float getTotal_score() {
        return total_score;
    }

    public void setTotal_score(Float total_score) {
        this.total_score = total_score;
    }

    public int getTopic_num() {
        return topic_num;
    }

    public void setTopic_num(int topic_num) {
        this.topic_num = topic_num;
    }

    public int getPermit_copy() {
        return permit_copy;
    }

    public void setPermit_copy(int permit_copy) {
        this.permit_copy = permit_copy;
    }

    public Float getPass_mark() {
        return pass_mark;
    }

    public void setPass_mark(Float pass_mark) {
        this.pass_mark = pass_mark;
    }

    public int getDisrupt_order() {
        return disrupt_order;
    }

    public void setDisrupt_order(int disrupt_order) {
        this.disrupt_order = disrupt_order;
    }

    public int getRepeat_test() {
        return repeat_test;
    }

    public void setRepeat_test(int repeat_test) {
        this.repeat_test = repeat_test;
    }

    public int getAuto_mack() {
        return auto_mack;
    }

    public void setAuto_mack(int auto_mack) {
        this.auto_mack = auto_mack;
    }
}
