package com.JapDetailed.week3_assignment.services;

import com.JapDetailed.week3_assignment.entities.Author;
import com.JapDetailed.week3_assignment.entities.Book;
import com.JapDetailed.week3_assignment.repositories.AuthorRepository;
import com.JapDetailed.week3_assignment.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository , AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks(Long id) {
        if(id != null) return List.of(bookRepository.findById(id).get());
        else return bookRepository.findAll();
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooksAfterGivenDate(LocalDate date) {
        return bookRepository.findByPublishedDateAfter(date);
    }

    public Set<Book> getBooksByAuthorId(Long authorId) {
        return authorRepository.findById(authorId).get().getBooks();
    }

    public Boolean deleteById(Long id) {
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    public Book bookMapWithAuthor(Long bookId,Long authorId) {
        Optional<Author> unmappedAuthor = authorRepository.findById(authorId);
        Optional<Book> unmappedBook = bookRepository.findById(bookId);

        return unmappedBook.flatMap(book ->
            unmappedAuthor.map(author -> {
                book.setAuthor(author);
                bookRepository.save(book);
                return book;
            })).orElse(null);
    }
}
