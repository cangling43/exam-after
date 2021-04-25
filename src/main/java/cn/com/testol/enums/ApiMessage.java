package cn.com.testol.enums;

public enum ApiMessage {
    error("请求失败",100),
    success("请求成功",200);

    private String name;
    private int value;
    ApiMessage(String name, int value) {
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
