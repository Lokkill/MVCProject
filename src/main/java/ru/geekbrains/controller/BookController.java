package ru.geekbrains.controller;

import ru.geekbrains.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    private static Connection connection;
    private static Statement statement;

    public List<Book> getBooks() throws SQLException {
        startConnection();
        List<Book> books = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("SELECT id, title, author FROM Books")){
            while (rs.next()){
                Book book = new Book();
                book.setId((long) rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                System.out.println(book.toString());
                books.add(book);
            }
            return books;
        }
    }

    public void startConnection(){
        try {
            connect();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:dbsqlite.db");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void disconnect(){
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
