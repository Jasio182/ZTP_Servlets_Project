package com.janmokrackiservletproject.servlets;

import com.janmokrackiservletproject.database.UserDbAccess;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        request.getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        try{
            String password = Arrays.stream(request.getParameterValues("Password")).findFirst().get();
            String login = Arrays.stream(request.getParameterValues("Login")).findFirst().get();
            UserDbAccess dbAccess = new UserDbAccess();
            if (dbAccess.UserExists(login, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("logged", true);
                session.setAttribute("userType", "Admin");
                request.getSession().setAttribute("username", login);
                session.setAttribute("password", password);
                request.getRequestDispatcher("DashboardServlet").forward(request, response);
            } else {
                throw new AuthenticationException();
            }
        }
        catch(Exception e)
        {
            request.getRequestDispatcher("/loginFailed.html").forward(request, response);
        }
    }
}