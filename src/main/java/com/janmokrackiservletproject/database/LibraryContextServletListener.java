package com.janmokrackiservletproject.database;

import com.janmokrackiservletproject.models.Book;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;

@WebListener
public class LibraryContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ArrayList<Book> books = new ArrayList<Book>();
        BookDbAccess dbAccess = new BookDbAccess();
        books = dbAccess.GetBooks();
        sce.getServletContext().setAttribute("books", books);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("books");
    }
}
