package com.library.book_manager.service;

import com.library.book_manager.model.Book;
import com.library.book_manager.repository.BookRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new EntityExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findBookById(id);
        if (book.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        return book.get();
    }

    public List<Book> getBookByAuthor(String author) {

        return bookRepository.findAllBookByAuthor(author);
    }

    public void deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        throw new RuntimeException("Book not found");

    }

    public Book updateBookAvailable(Long id, Boolean available) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailable(available);
        return bookRepository.save(book);

    }

    public Book updateBook(Long id, Book book) {
        Optional<Book> bookOptional = bookRepository.findBookById(id);
        if (bookOptional.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
            Book bookToUpdate= bookOptional.get();
            bookToUpdate.setAvailable(book.getAvailable());
            return bookRepository.save(book);
        }

    }
