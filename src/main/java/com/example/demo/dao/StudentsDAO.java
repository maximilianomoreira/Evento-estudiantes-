package com.example.demo.dao;

import com.example.demo.db.databaseConection;
import com.example.demo.model.Students;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentsDAO {



    public void createStudent(Students s) {
        String sql = "INSERT INTO Students(name,email,password) VALUES (?,?,?)";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPassword());
            ps.executeUpdate();
            System.out.println("Student created");
        } catch (Exception e) {
            System.out.println("Error creating student: " + e.getMessage());
        }
    }
    public void getStudentById(int id) {
        String sql = "SELECT * FROM Students WHERE id = ?";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement()
        } catch (RuntimeException e)
            {
            System.out.println("Error getting student by id: " + e.getMessage());
            }
    }







}
