package cn.com.testol.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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


    @Override
    public String toString() {
        return "Topic{" +
                "t_id=" + t_id +
                ", u_id=" + u_id +
                ", subject_id=" + subject_id +
                ", question='" + question + '\'' +
                ", choice='" + choice + '\'' +
                ", photo='" + photo + '\'' +
                ", topic_type=" + topic_type +
                ", correct_answer='" + correct_answer + '\'' +
                ", score='" + score + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", analysis='" + analysis + '\'' +
                '}';
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(int topic_type) {
        this.topic_type = topic_type;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
