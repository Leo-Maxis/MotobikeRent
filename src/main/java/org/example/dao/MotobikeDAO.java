package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.Motobike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotobikeDAO {
    public Motobike insert(Motobike entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into Motobike(Name, YearModel, TypeId, TypeName, Price) values (?, ?, ?, ?, ?)";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            ptsmt.setString(1, entity.getName());
            ptsmt.setInt(2, entity.getYearModel());
            ptsmt.setInt(3, entity.getMotoType());
            ptsmt.setString(4, entity.getTypeName());
            ptsmt.setDouble(5, entity.getPrice());
            ptsmt.executeUpdate();

            ResultSet rs = ptsmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        }
    }

    public List<Motobike> findAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from motobike";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<Motobike> list = new ArrayList<>();
            try (ResultSet rs = ptsmt.executeQuery()) {
                while (rs.next()) {
                    Motobike entity = new Motobike();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setYearModel(rs.getInt("yearModel"));
                    entity.setMotoType(rs.getInt("TypeId"));
                    entity.setTypeName(rs.getString("TypeName"));
                    entity.setPrice(rs.getDouble("Price"));
                    list.add(entity);
                }
            }
            return list;
        }
    }

    public boolean update(Motobike entity) throws SQLException, ClassNotFoundException {
        String sql = "update motobike set name =?, YearModel=?, TypeId=?, TypeName=?, Price=? where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setString(1, entity.getName());
            ptsmt.setInt(2, entity.getYearModel());
            ptsmt.setInt(3, entity.getMotoType());
            ptsmt.setString(4, entity.getTypeName());
            ptsmt.setDouble(5, entity.getPrice());
            ptsmt.setInt(6, entity.getId());
            return ptsmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from motobike where id = ?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);
            return ptsmt.executeUpdate() > 0;
        }
    }

    public Motobike findById(int id) throws SQLException, ClassNotFoundException {
        String sql = " select * from motobike where id =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            List<Motobike> list = new ArrayList<>();
            ptsmt.setInt(1, id);
            try (ResultSet rs = ptsmt.executeQuery()) {
                if (rs.next()) {
                    Motobike entity = new Motobike();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setYearModel(rs.getInt("YearModel"));
                    entity.setMotoType(rs.getInt("typeId"));
                    entity.setTypeName(rs.getString("typeName"));
                    entity.setPrice(rs.getDouble("price"));
                    return entity;
                }
            }
            return null;
        }
    }
}
