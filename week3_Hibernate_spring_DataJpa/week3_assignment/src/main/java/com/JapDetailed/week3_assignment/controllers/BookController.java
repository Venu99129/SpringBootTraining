package com.JapDetailed.week3_assignment.controllers;

import com.JapDetailed.week3_assignment.entities.Book;
import com.JapDetailed.week3_assignment.services.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public Book createBook(@RequestBody(required = false) Book book){
        return bookService.createBook(book);
    }

    @GetMapping()
    public List<Book> getAllBooks(@RequestParam Long id){
        return bookService.getBooks(id);
    }

    @PutMapping()
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @PutMapping(path = "/{bookId}/mapAuthor/{authorId}")
    public Book bookMapWithAuthor(@PathVariable Long bookId , @PathVariable Long authorId){
        return bookService.bookMapWithAuthor(bookId,authorId);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteBookById(@PathVariable Long id){
        return bookService.deleteById(id);
    }

    @GetMapping(path = "/{date}")
    public List<Book> getBookAfterGivenDate(@PathVariable LocalDate date){
        return bookService.getBooksAfterGivenDate(date);
    }

    @GetMapping("/authors/{authorId}")
    public Set<Book> getBooksByAuthorId(@PathVariable Long authorId){
        return bookService.getBooksByAuthorId(authorId);
    }

}
