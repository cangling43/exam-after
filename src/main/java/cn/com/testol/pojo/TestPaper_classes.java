package cn.com.testol.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
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


}
