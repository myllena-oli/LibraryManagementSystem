package org.example.service;

import org.example.dao.BookDAO;
import org.example.model.Book;
import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(Long bookId) {
        return bookDAO.getBook(bookId);
    }

    public void createBook(String name, String author, LocalDate releaseDate) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setAuthor(author);
        newBook.setReleaseDate(releaseDate);

        bookDAO.createBook(newBook);
    }

    public void updateBook(Long bookId, String name, String author, LocalDate releaseDate) {
        Book existingBook = bookDAO.getBook(bookId);

        if (existingBook != null) {
            if (name != null && !name.isEmpty()) {
                existingBook.setName(name);
            }
            if (author != null && !author.isEmpty()) {
                existingBook.setAuthor(author);
            }
            if (releaseDate != null) {
                existingBook.setReleaseDate(releaseDate);
            }

            bookDAO.updateBook(existingBook);
        }
    }

    public void deleteBook(Long bookId) {
        bookDAO.deleteBook(bookId);
    }
}
