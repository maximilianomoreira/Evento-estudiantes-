package com.example.demo.servlet;

import com.example.demo.dao.EventsDAO;
import com.example.demo.model.Events;
import com.example.demo.model.Students;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EventsServlet", value = "/events")
public class EventServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Students student = (Students) req.getSession().getAttribute("student");
        if (student == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        boolean showPast = "1".equals(req.getParameter("past"));
        EventsDAO dao = new EventsDAO();
        List<Events> events = showPast
                ? dao.listPastByStudent(student.getId())
                : dao.listNextByStudent(student.getId());

        req.setAttribute("events", events);
        req.setAttribute("showPast", showPast);
        req.getRequestDispatcher("/pages/events.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Students student = (Students) req.getSession().getAttribute("student");
        if (student == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        EventsDAO dao = new EventsDAO();

        try {
            switch (action) {
                case "add": {
                    String title = req.getParameter("title");
                    String description = req.getParameter("description");
                    String eventType = req.getParameter("eventType");
                    LocalDate date = LocalDate.parse(req.getParameter("eventDate"));

                    if (title == null || title.isBlank() || date == null) {
                        req.setAttribute("error", "Completa el título y la fecha.");
                        doGet(req, res);
                        return;
                    }

                    Events ev = new Events(student.getId(), title, description, eventType, date);
                    dao.createEvent(ev);
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    String title = req.getParameter("title");
                    String description = req.getParameter("description");
                    String eventType = req.getParameter("eventType");
                    LocalDate date = LocalDate.parse(req.getParameter("eventDate"));

                    Events ev = new Events(id, student.getId(), title, description, eventType, date);
                    dao.updateEvent(ev);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    dao.deleteEvent(id, student.getId());
                    break;
                }
                default:
                    break;
            }

            String past = req.getParameter("past");
            String suffix = (past != null && past.equals("1")) ? "?past=1" : "";
            res.sendRedirect(req.getContextPath() + "/events" + suffix);

        } catch (Exception e) {
            req.setAttribute("error", "Ocurrio un error al procesar la accion.");
            doGet(req, res);
        }
    }
}