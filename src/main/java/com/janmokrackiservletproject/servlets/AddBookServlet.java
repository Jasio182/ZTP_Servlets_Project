package com.janmokrackiservletproject.servlets;

import com.janmokrackiservletproject.dataLogic.BookDbAccess;
import com.janmokrackiservletproject.models.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "AddBookServlet", value = "/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletContext context = getServletContext();
        ArrayList<Book> books = (ArrayList<Book>) context.getAttribute("books");
        try{
            String title = Arrays.stream(request.getParameterValues("TitleOfBookToAdd")).findFirst().get();
            String author = Arrays.stream(request.getParameterValues("AuthorOfBookToAdd")).findFirst().get();
            String year = Arrays.stream(request.getParameterValues("YearOfBookToAdd")).findFirst().get();
            Book book = new Book(title, author, Integer.parseInt(year));
            books.add(book);
            new BookDbAccess().AddBook(title, author, Integer.parseInt(year));
            context.setAttribute("books", books);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("AdminServlet").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
