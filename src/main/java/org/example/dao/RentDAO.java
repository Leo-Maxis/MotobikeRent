package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {
    public List<Rent> findAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from rent";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<Rent> list = new ArrayList<>();
            try (ResultSet rs = ptsmt.executeQuery()) {
                while (rs.next()) {
                    Rent entity = new Rent();
                    entity.setId(rs.getInt("id"));
                    entity.setCustomerId(rs.getInt("CustomerId"));
                    entity.setCustomerName(rs.getString("name"));
                    entity.setPhoneNumber(rs.getString("PhoneNumber"));
                    entity.setCccd(rs.getString("cccd"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setMotobikeId(rs.getInt("motoId"));
                    entity.setMotobikeName(rs.getString("motoName"));
                    entity.setDays(rs.getInt("days"));
                    entity.setTotal(rs.getDouble("total"));
                    entity.setStartDate(rs.getDate("startDate"));
                    entity.setEndDate(rs.getDate("endDate"));
                    list.add(entity);
                }
            }
            return list;
        }
    }

    public Rent findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from rent where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<Rent> list = new ArrayList<>();
            ptsmt.setInt(1, id);
            try (ResultSet rs = ptsmt.executeQuery()) {
                if (rs.next()) {
                    Rent entity = new Rent();
                    entity.setId(rs.getInt("id"));
                    entity.setCustomerId(rs.getInt("CustomerId"));
                    entity.setCustomerName(rs.getString("name"));
                    entity.setPhoneNumber(rs.getString("PhoneNumber"));
                    entity.setCccd(rs.getString("cccd"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setMotobikeId(rs.getInt("motoId"));
                    entity.setMotobikeName(rs.getString("motoName"));
                    entity.setDays(rs.getInt("days"));
                    entity.setTotal(rs.getDouble("total"));
                    entity.setStartDate(rs.getDate("startDate"));
                    entity.setEndDate(rs.getDate("endDate"));
                    return entity;
                }
            }
            return null;
        }
    }
}
