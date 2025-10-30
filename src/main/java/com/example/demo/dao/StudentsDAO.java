package com.example.demo.dao;

import com.example.demo.db.databaseConection;
import com.example.demo.model.Students;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentsDAO {

    public boolean createStudent(Students s) {
        String sql = "INSERT INTO students(name, email, password) VALUES (?,?,?)";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPassword());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear estudiante: " + e.getMessage());
            return false;
        }
    }
    public Students validarLogin(String email, String password) {
        String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Students(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al validar login: " + e.getMessage());
        }
        return null;
    }
}
