package com.revature.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private  String name ;
    private String type;
    private float amount;
    private int user_id;

    public Account(){};
    public Account(String name, String type, float amount, int user_id) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
