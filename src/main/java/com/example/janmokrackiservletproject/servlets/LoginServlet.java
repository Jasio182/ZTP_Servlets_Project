package com.example.janmokrackiservletproject.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        try{
            String login = Arrays.stream(request.getParameterValues("Login")).findFirst().get();
            RequestDispatcher dispatcher;
            if ("admin".equals(login)) {
                dispatcher = request.getRequestDispatcher("AdminLoginServlet");
            } else {
                dispatcher = request.getRequestDispatcher("UserLoginServlet");
            }
            request.getSession().setAttribute("username", login);
            dispatcher.forward(request, response);
        }
        catch(Exception e)
        {
            request.getRequestDispatcher("/loginFailed.html").forward(request, response);
        }
    }
}
