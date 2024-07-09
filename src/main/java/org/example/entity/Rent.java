package org.example.entity;

import java.io.Serializable;
import java.util.Date;

public class Rent implements Serializable {
    private int id;
    private int customerId;
    private String customerName;
    private String phoneNumber;
    private String cccd;
    private String address;
    private int motoId;
    private String motoName;
    private int days;
    private double total;
    private Date startDate;
    private Date endDate;

    public Rent() {
    }

    public Rent(int id, int customerId, String customerName, String phoneNumber, String cccd, String address, int motoId, String motoName, int days, double total, Date startDate, Date endDate) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.cccd = cccd;
        this.address = address;
        this.motoId = motoId;
        this.motoName = motoName;
        this.days = days;
        this.total = total;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMotoId() {
        return motoId;
    }

    public void setMotoId(int motoId) {
        this.motoId = motoId;
    }

    public String getMotoName() {
        return motoName;
    }

    public void setMotoName(String motoName) {
        this.motoName = motoName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
