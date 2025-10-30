package com.example.demo.dao;
import com.example.demo.db.databaseConection;
import com.example.demo.model.Events;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {

    public List<Events> listNextByStudent(int studentId) {
        List<Events> out = new ArrayList<>();
        String sql = "SELECT id, student_id, title, description, event_date, event_type " +
                "FROM events WHERE student_id = ? AND event_date >= CURDATE() ORDER BY event_date ASC";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Events e = new Events(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("event_type"),
                        rs.getDate("event_date").toLocalDate()
                );
                out.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error listNextByStudent: " + e.getMessage());
        }
        return out;
    }

    public List<Events> listPastByStudent(int studentId) {
        List<Events> out = new ArrayList<>();
        String sql = "SELECT id, student_id, title, description, event_date, event_type " +
                "FROM events WHERE student_id = ? AND event_date < CURDATE() ORDER BY event_date DESC";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Events e = new Events(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("event_type"),
                        rs.getDate("event_date").toLocalDate()
                );
                out.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error listPastByStudent: " + e.getMessage());
        }
        return out;
    }

    public boolean createEvent(Events ev) {
        String sql = "INSERT INTO events(student_id, title, description, event_date, event_type) VALUES (?,?,?,?,?)";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ev.getStudentId());
            ps.setString(2, ev.getTitle());
            ps.setString(3, ev.getDescription());
            ps.setDate(4, Date.valueOf(ev.getEventDate()));
            ps.setString(5, ev.getEventType());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error creating event: " + e.getMessage());
            return false;
        }
    }

    // Protegemos por student_id para asegurar ownership
    public boolean updateEvent(Events ev) {
        String sql = "UPDATE events SET title=?, description=?, event_date=?, event_type=? " +
                "WHERE id=? AND student_id=?";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ev.getTitle());
            ps.setString(2, ev.getDescription());
            ps.setDate(3, Date.valueOf(ev.getEventDate()));
            ps.setString(4, ev.getEventType());
            ps.setInt(5, ev.getId());
            ps.setInt(6, ev.getStudentId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error updateEvent: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEvent(int eventId, int studentId) {
        String sql = "DELETE FROM events WHERE id=? AND student_id=?";
        Connection con = databaseConection.getInstancia().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, eventId);
            ps.setInt(2, studentId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error deleteEvent: " + e.getMessage());
            return false;
        }
    }
}