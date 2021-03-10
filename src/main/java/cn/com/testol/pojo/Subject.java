package cn.com.testol.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "subjects")
@Data
public class Subject {
    @Id
    private int s_id;
    @Column(name = "s_name")
    private String name;
    private int create_id;
    private String create_date;


}
