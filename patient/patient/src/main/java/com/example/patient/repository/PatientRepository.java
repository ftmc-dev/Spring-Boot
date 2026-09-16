package com.example.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByAuthor(String author);

    boolean existsByIsbn(String isbn);

    Optional<Book> findBookById(Long id);
}
