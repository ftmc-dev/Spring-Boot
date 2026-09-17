package com.library.book_manager;

import com.library.book_manager.model.Book;
import com.library.book_manager.repository.BookRepository;
import com.library.book_manager.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    public void saveBookTest() {
        //Given
        Book book = new Book();

        Book savedBook = new Book();

        when(bookRepository.existsByIsbn(book.getIsbn())).thenReturn(false);
        when(bookRepository.save(book)).thenReturn(savedBook);

        //When
        Book result = bookService.create(book);

        //Then
        assertThat(result).isEqualTo(savedBook);
        verify(bookRepository).save(book);
    }

    @Test
    public void getAllBookTest() {
        //Given
        List<Book> getAllBooks = new ArrayList<>();

        when(bookRepository.findAll()).thenReturn(getAllBooks);

        //When
        List<Book> allBook = bookService.getAllBook();

        //Then
        assertThat(allBook).isEqualTo(getAllBooks);
        verify(bookRepository).findAll();
    }

    @Test
    public void getBookByIdPassTest() {
        //Given
        Book book = new Book();
        Optional<Book> getBookById = Optional.of(book);

        Long id = 1L;
        when(bookRepository.findBookById(id)).thenReturn(getBookById);

        //When
        Book findBook = bookService.getBookById(id);

        //Then
        assertThat(findBook).isEqualTo(getBookById.get());
        verify(bookRepository).findBookById(id);
    }

    @Test
    public void getBookByIdFailTest() {
        //Given
        Book book = new Book();
        Optional<Book> getBookById = Optional.empty();

        Long id = 1L;
        when(bookRepository.findBookById(id)).thenReturn(getBookById);

        //When

        //Then
        assertThatThrownBy(() -> bookService.getBookById(id)).isInstanceOf(RuntimeException.class).hasMessage("Book not found");
    }


    @Test
    public void getBooksByAuthorPassTest() {
        //Given
        String author = "author";
        List<Book> getBookByAuthor = new ArrayList<>();

        when(bookRepository.findAllBookByAuthor(author)).thenReturn(getBookByAuthor);

        //When
        List<Book> findByAuthor = bookService.getBookByAuthor(author);

        //Then
        assertThat(findByAuthor).isEqualTo(getBookByAuthor);
        verify(bookRepository).findAllBookByAuthor(author);
    }

    @Test
    public void getBooksByAuthorFailTest() {
        //Given
        Book book = new Book();

        List<Book> getBookByAuthor = new ArrayList<>();

        String author = "John Doe";
        when(bookRepository.findAllBookByAuthor(author)).thenReturn(getBookByAuthor);

        //When

        //Then
        assertThatThrownBy(() -> bookService.getBookByAuthor(author)).isInstanceOf(RuntimeException.class).hasMessage("Book not found");
    }

    @Test
    public void deleteBookByIdTest() {
        //Given
        Long bookId = 1L;

        when(bookRepository.existsById(bookId)).thenReturn(true);

        //When
        bookService.deleteBookById(bookId);

        //Then
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    public void updateBookAvailableTest() {
        //Given
        Book book = new Book();
        Optional<Book> books = Optional.of(book);

        when(bookRepository.findBookById(1L)).thenReturn(books);
        when(bookRepository.save(book)).thenReturn(book);

        //When
        Book book1 = bookService.updateBookAvailable(1L, false);

        //Then
        assertThat(book1).isEqualTo(books.get());
        verify(bookRepository).findBookById(1L);
    }
}
