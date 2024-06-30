package org.example.dao;

import org.example.database.DBHelper;
import org.example.entity.UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    public UserLogin loginuser(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from UserLogin where username =? and password =?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ptsmt = conn.prepareStatement(sql);) {
            List<UserLogin> list = new ArrayList<>();
            ptsmt.setString(1, username);
            ptsmt.setString(2, password);
            try (ResultSet rs = ptsmt.executeQuery()) {
                if (rs.next()) {
                    UserLogin userLogin = new UserLogin();
                    userLogin.getUsername();
                    userLogin.getPassword();
                    return userLogin;
                }
            }
            return null;
        }
    }
}
