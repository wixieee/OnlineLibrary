package edu.library.service;

import edu.library.dto.BookRequest;
import edu.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(Long id);
    List<Book> getBooksByTitle(String title);
    Book addBook(BookRequest book);
    Book updateBook(Long id, BookRequest book);
    void deleteBook(Long id);
}
