package com.example.janmokrackiservletproject.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("TestServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        login = Arrays.stream(request.getParameterValues("Login")).findFirst().get();
        request.getRequestDispatcher("AdminLoginServlet").forward(request, response);
        RequestDispatcher dispatcher;
        if ("admin".equals(login)) {
            dispatcher = request.getRequestDispatcher("AdminLoginServlet");
        } else {
            dispatcher = request.getRequestDispatcher("UserLoginServlet");
        }
        //dispatcher.forward(request, response);
    }
}
