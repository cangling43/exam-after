package cn.com.testol.pojo;



//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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


    public String getNumber() {
        String id=getC_id()+"";
        while (id.length()<5){
            id="0"+id;
        }
        return "C"+id;
    }


}





