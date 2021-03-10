package cn.com.testol.enums;

public enum  Classesjoinway {
    NO("不允许加入",0),
    ALL("允许任何人加入",1),
    APPLY("需要管理员同意申请",2)
    ;

    private String name;
    private int value;
    Classesjoinway(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
