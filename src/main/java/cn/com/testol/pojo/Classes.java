package cn.com.testol.pojo;



//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class Classes {
    @Id
    private int c_id;
    private String number;
    @Column(name="c_name")
    private String name;
    private String introduction;
    private int creator_id;
    private String creatorName;
    private int people_num;
    private String joinWay;
    private String create_date;



    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getNumber() {
        String id=getC_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return "C"+id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public String getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(String joinWay) {
        this.joinWay = joinWay;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

}





