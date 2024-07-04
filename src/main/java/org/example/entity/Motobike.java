package org.example.entity;

import java.io.Serializable;
import java.util.Date;

public class Motobike implements Serializable {
    private int id;
    private String name;
    private int yearModel;
    private int motoType;
    private String typeName;
    private double price;

    public Motobike() {
    }

    public Motobike(int id, String name, int yearModel, int motoType, String typeName, double price) {
        this.id = id;
        this.name = name;
        this.yearModel = yearModel;
        this.motoType = motoType;
        this.typeName = typeName;
        this.price = price;
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

    public int getYearModel() {
        return yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

    public int getMotoType() {
        return motoType;
    }

    public void setMotoType(int motoType) {
        this.motoType = motoType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
