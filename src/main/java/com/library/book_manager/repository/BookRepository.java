package com.library.book_manager.repository;

import com.library.book_manager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllBookByAuthor(String author);

    boolean existsByIsbn(String isbn);

    Optional<Book> findBookById(Long id);
}
