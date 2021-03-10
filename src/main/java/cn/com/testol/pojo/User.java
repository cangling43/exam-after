package cn.com.testol.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
@Data
public class User {
    @Id
    private int u_id;
    private String number;
    @Column(name="u_name")
    private String name;
    private String sex;
    private String phone;
    private String photo;
    private String email;
    private String status;
    private String work;
    private String create_date;


    public String getNumber() {
        String id=getU_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return "U"+id;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
