package com.example.demo.servlet;

import com.example.demo.dao.StudentsDAO;
import com.example.demo.model.Students;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");

        if (name == null || email == null || password == null || confirm == null ||
                name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            request.setAttribute("error", "Completa todos los campos.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        StudentsDAO dao = new StudentsDAO();
        Students s = new Students(name, email, password);

        if (dao.createStudent(s)) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Error al registrar el estudiante");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }
}