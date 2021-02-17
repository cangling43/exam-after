package cn.com.testol.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "subjects")
public class Subject {
    @Id
    private int s_id;
    @Column(name = "s_name")
    private String name;
    private int create_id;
    private String create_date;


    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
