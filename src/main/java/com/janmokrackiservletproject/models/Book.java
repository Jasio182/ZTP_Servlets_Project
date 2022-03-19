package com.janmokrackiservletproject.models;

public class Book {

    protected String title;
    protected String author;
    protected int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "<tr>\n" +
                "   <td>"+title+"</th>\n" +
                "   <td>"+author+"</th>\n" +
                "   <td>"+year+"</th>\n" +
                "</tr>\n";
    }
}
