package com.janmokrackiservletproject.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/DashboardServlet")
public class DashboardFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            if((boolean)session.getAttribute("logged"))
            {

                if((int)session.getAttribute("userType") == 0)
                    dispatcher = request.getRequestDispatcher("AdminServlet");
                else
                    dispatcher = request.getRequestDispatcher("DashboardServlet");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            dispatcher.forward(request, response);
        }

    }
}
