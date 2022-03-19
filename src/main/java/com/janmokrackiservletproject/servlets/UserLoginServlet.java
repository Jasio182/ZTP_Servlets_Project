package com.janmokrackiservletproject.servlets;

import com.janmokrackiservletproject.dataLogic.PasswordHashing;
import com.janmokrackiservletproject.dataLogic.UserDbAccess;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    HashMap<String, String> Users;

    @Override
    public void init() throws ServletException {
        super.init();
        UserDbAccess dbAccess = new UserDbAccess();
        Users = dbAccess.FillUsersMap();
    }

    private boolean checkUser(String login, String password){
        try {
            String pass = Users.get(login);
            return pass.equals(password);
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        request.getRequestDispatcher("login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        try{
            String password = Arrays.stream(request.getParameterValues("Password")).findFirst().get();
            String hashedPassword = PasswordHashing.HashPassword(password);
            String login = Arrays.stream(request.getParameterValues("Login")).findFirst().get();
            if (checkUser(login, hashedPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("logged", true);
                session.setAttribute("userType", 1);
                response.sendRedirect("DashboardServlet");
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
