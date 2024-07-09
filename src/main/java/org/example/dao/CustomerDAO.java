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
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "update customer set name=?, PhoneNumber=?, count=? where id=?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptmst = conn.prepareStatement(sql)) {
            ptmst.setString(1, entity.getName());
            ptmst.setString(2, entity.getPhoneNumber());
            ptmst.setInt(3, entity.getCount());
            ptmst.setInt(4, entity.getId());
            return ptmst.executeUpdate() > 0;
        }
    }

    public boolean updateCount(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "update customer set count=count + 1 where id=?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptmst = conn.prepareStatement(sql)) {
            ptmst.setInt(1, entity.getId());
            return ptmst.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from customer where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);
            return ptsmt.executeUpdate() > 0;
        }
    }

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

    public Customer insert(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into customer (name, phoneNumber, count) values (?,?,?)";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ptsmt.setString(1, entity.getName());
            ptsmt.setString(2, entity.getPhoneNumber());
            ptsmt.setInt(3, entity.getCount());
            ptsmt.executeUpdate();
            ResultSet rs = ptsmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        }
    }
}
