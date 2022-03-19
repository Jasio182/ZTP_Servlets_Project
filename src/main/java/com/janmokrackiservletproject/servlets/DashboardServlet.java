package com.janmokrackiservletproject.servlets;

import com.janmokrackiservletproject.models.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    ArrayList<Book> books;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        books = (ArrayList<Book>) context.getAttribute("books");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Dashboard</title>");
        out.println("</head>");
        out.println("<style>\n" +
                "table, td, th {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>");
        out.println("<body>");
        out.println("<H1>Books</H1>");
        out.println("<table>\n" +
                "  <tr>\n" +
                "    <th>Title</th>\n" +
                "    <th>Author</th>\n" +
                "    <th>Year</th>\n" +
                "  </tr>\n");
        for (Book book:
             books) {
            out.println(book.toString());
        }
        out.println("</table>");
        out.println("<form action=\"LogoutServlet\">\n");
        out.println("<input type=\"submit\" value=\"Logout\">\n");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
