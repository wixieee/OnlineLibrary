package edu.library.service;

import edu.library.dto.BookRequest;
import edu.library.exception.ResourceNotFoundException;
import edu.library.model.Book;
import edu.library.repository.BookRepository;
import edu.library.utils.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Book addBook(BookRequest book) {
        return bookRepository.save(bookMapper.toEntity(book));
    }

    @Override
    public Book updateBook(Long id, BookRequest book) {
        Book existing = getBookById(id);
        bookMapper.updateEntityFromDto(book, existing);

        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(getBookById(id));
    }
}
