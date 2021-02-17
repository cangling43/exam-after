package cn.com.testol.pojo;

public class User_classes {
    private int u_id;
    private int c_id;
    private String status;
    private String enter_date;

    private Classes classes;
    private User user;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnter_date() {
        return enter_date;
    }

    public void setEnter_date(String enter_date) {
        this.enter_date = enter_date;
    }
}
