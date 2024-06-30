package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.MotoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoTypeDAO {

    public List<MotoType> findAll() throws SQLException, ClassNotFoundException {
        String sql = "Select * from MotoType";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<MotoType> list = new ArrayList<>();
            try(ResultSet rs = ptsmt.executeQuery();) {
                while (rs.next()) {
                    MotoType entity = new MotoType();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    list.add(entity);
                }
            }
            return list;
        }
    }

    public MotoType insert(MotoType entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into MotoType(name) values (?)";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ptsmt.setString(1, entity.getName());
            ptsmt.executeUpdate();
            ResultSet rs = ptsmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        }
    }

    public MotoType update(MotoType entity) throws SQLException, ClassNotFoundException {
        String sql = "update MotoType set name =? where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setString(1, entity.getName());
            ptsmt.setInt(2, entity.getId());
            ptsmt.executeUpdate();
            return entity;
        }
    }
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from MotoType where id =?";

        try(Connection conn = DBHelper.getConnection();
            PreparedStatement ptms = conn.prepareStatement(sql)) {
            ptms.setInt(1, id);
            return ptms.executeUpdate() > 0;
        }
    }

    public MotoType findById(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from MotoType where id =?";
        try (Connection conn = DBHelper.getConnection();
            PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);
            try (ResultSet rs = ptsmt.executeQuery()) {
                while (rs.next()) {
                    MotoType entity = new MotoType();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    return entity;
                }
            }
            return null;
        }
    }
}
