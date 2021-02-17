package cn.com.testol.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TestPaper_classes {
    @Id
    private int tc_id;
    private int tp_id;//试卷id
    private int c_id;//班级id
    private String release_time;//发布时间
    private String start_date;//开始时间
    private String deadline;//截至时间
    @Column(name = "publish_answer")
    private int publishAnswer;//是否公布答案   0:不公布   1:公布答案
    @Column(name = "publish_score")
    private int publishScore;//是否公布分数   0:不公布   1:公布
    @Column(name = "tc_status")
    private int status;//状态 0:待批改 1:批改完成

    @Transient
    private Classes classes;
    @Transient
    private TestPaper testPaper;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTc_id() {
        return tc_id;
    }

    public void setTc_id(int tc_id) {
        this.tc_id = tc_id;
    }


    public int getPublishAnswer() {
        return publishAnswer;
    }

    public void setPublishAnswer(int publishAnswer) {
        this.publishAnswer = publishAnswer;
    }

    public int getPublishScore() {
        return publishScore;
    }

    public void setPublishScore(int publishScore) {
        this.publishScore = publishScore;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
