package com.example.demo.servlet;

import com.example.demo.dao.StudentsDAO;
import com.example.demo.model.Students;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StudentsDAO dao = new StudentsDAO();
        Students student = dao.validarLogin(email, password);

        if (student != null) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("student", student);
            response.sendRedirect(request.getContextPath() + "/pages/events.jsp");
        } else {
            request.setAttribute("error", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}