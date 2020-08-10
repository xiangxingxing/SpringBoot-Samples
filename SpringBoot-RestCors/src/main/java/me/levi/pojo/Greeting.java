package me.levi.pojo;

/**
 * Spring使用Jackson JSON库自动将Greeting类型的实例编组为JSON。
 * */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting() {
        this.id = -1;
        this.content = "";
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
