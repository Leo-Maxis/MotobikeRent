package org.example.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String phoneNumber;
    private int count;

    public Customer() {
    }

    public Customer(int id, String name, String phoneNumber, int count) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
