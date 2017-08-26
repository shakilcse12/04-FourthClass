package com.hellohasan.forthclass;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("sender_name")
    private String senderName;
    @SerializedName("age")
    private int age;

    public DataModel(String senderName, int age) {
        this.senderName = senderName;
        this.age = age;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
