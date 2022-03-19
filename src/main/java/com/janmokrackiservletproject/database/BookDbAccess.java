package com.janmokrackiservletproject.database;

import com.janmokrackiservletproject.models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class BookDbAccess extends DbAccess  {

    public boolean AddBook(String title, String author, int year)
    {
        try {
            Statement statement = GetConnection().createStatement();
            String query = "INSERT INTO Books VALUES ('"+title+"', '"+author+"', '"+ year +"');";
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean RemoveBook(String title)
    {
        try {
            Statement statement = GetConnection().createStatement();
            String query = "DELETE FROM Books Where title = '"+title+"';";
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Book> GetBooks()
    {
        try {
            Statement statement = GetConnection().createStatement();
            String query = "SELECT * FROM Books";
            ResultSet result = statement.executeQuery(query);
            ArrayList<Book> books = new ArrayList<Book>();
            while (result.next()) {
                books.add(new Book(result.getString("title"), result.getString("author"), result.getInt("year")));
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Book>();
        }
    }
}
