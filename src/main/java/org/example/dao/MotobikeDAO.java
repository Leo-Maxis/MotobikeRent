package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.Motobike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            ResultSet rs = ptsmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            return entity;
        }
    }
}
