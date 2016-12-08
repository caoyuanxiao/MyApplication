package com.example.smile.fristapplication.bean;

/**
 * Created by Smile on 2016/12/8.
 */

public class ChatMessage {

    int type = 1;
    String name;

    public ChatMessage(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
