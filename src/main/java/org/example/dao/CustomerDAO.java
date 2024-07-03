package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from customer";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<Customer> list = new ArrayList<>();
            try (ResultSet rs = ptsmt.executeQuery()) {
                while (rs.next()) {
                    Customer entity = new Customer();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setPhoneNumber(rs.getString("phoneNumber"));
                    entity.setCount(rs.getInt("count"));
                    list.add(entity);
                }
            }
            return list;
        }
    }

    public Customer findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from customer where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);
            try (ResultSet rs = ptsmt.executeQuery()) {
                if (rs.next()) {
                    Customer entity = new Customer();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setPhoneNumber(rs.getString("phoneNumber"));
                    entity.setCount(rs.getInt("count"));
                    return entity;
                }
            }
            return null;
        }
    }
}
