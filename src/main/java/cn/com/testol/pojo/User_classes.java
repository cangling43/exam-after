package cn.com.testol.pojo;

import lombok.Data;

@Data
public class User_classes {
    private int u_id;
    private int c_id;
    private String status;
    private String enter_date;

    private Classes classes;
    private User user;

}
