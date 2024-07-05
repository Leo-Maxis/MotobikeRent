package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.Rent;

import java.sql.*;
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

    public boolean update(Rent entity) throws SQLException, ClassNotFoundException {
        String sql = "update rent set customerId =?, name =?, phoneNumber=?, cccd=?, address=?, motoId=?, motoName=?, days=?, total=?, startDate=?, endDate=? where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, entity.getCustomerId());
            ptsmt.setString(2, entity.getCustomerName());
            ptsmt.setString(3, entity.getPhoneNumber());
            ptsmt.setString(4, entity.getCccd());
            ptsmt.setString(5, entity.getAddress());
            ptsmt.setInt(6, entity.getMotobikeId());
            ptsmt.setString(7, entity.getMotobikeName());
            ptsmt.setInt(8, entity.getDays());
            ptsmt.setDouble(9, entity.getTotal());
            java.sql.Date startDate = new Date(entity.getStartDate().getTime());
            ptsmt.setDate(10, startDate);
            java.sql.Date endDate = new Date(entity.getEndDate().getTime());
            ptsmt.setDate(11, endDate);
            ptsmt.setInt(12, entity.getId());
            return ptsmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from rent where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);
            return ptsmt.executeUpdate() > 0;
        }
    }

    public Rent insert(Rent entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into rent (customerId, name, phoneNumber, cccd, address, motoId, motoName, days, total, startDate, endDate values (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptmst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ptmst.setInt(1, entity.getCustomerId());
            ptmst.setString(2, entity.getCustomerName());
            ptmst.setString(3, entity.getPhoneNumber());
            ptmst.setString(4, entity.getCccd());
            ptmst.setString(5, entity.getAddress());
            ptmst.setInt(6, entity.getMotobikeId());
            ptmst.setString(7, entity.getMotobikeName());
            ptmst.setInt(8, entity.getDays());
            ptmst.setDouble(9, entity.getTotal());
            java.sql.Date startDate = new Date(entity.getStartDate().getTime());
            ptmst.setDate(10, startDate);
            java.sql.Date endDate = new Date(entity.getEndDate().getTime());
            ptmst.setDate(11, endDate);
            ptmst.executeUpdate();

            ResultSet rs = ptmst.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        }
    }
}
