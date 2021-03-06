package ru.geekbrains.view;

import lombok.SneakyThrows;
import ru.geekbrains.controller.BookController;
import ru.geekbrains.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class BookServlet extends HttpServlet {
    BookController bookController = new BookController();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        List<Book> books = bookController.getBooks();
        for (Book b : books){
            printWriter.write(b.toString());
        }
        printWriter.close();
    }
}
