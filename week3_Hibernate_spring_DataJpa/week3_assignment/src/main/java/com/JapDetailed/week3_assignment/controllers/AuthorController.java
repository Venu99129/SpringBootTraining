package com.JapDetailed.week3_assignment.controllers;

import com.JapDetailed.week3_assignment.entities.Author;
import com.JapDetailed.week3_assignment.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> getAuthors(@RequestParam(required = false) Long id){
        return authorService.getAuthors(id);
    }

    @PostMapping()
    public Author createAuthor(@RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @PutMapping()
    public Author updateAuthor(@RequestBody Author author){
        return authorService.updateAuthor(author);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteAuthorById(@PathVariable Long id){
        return authorService.deleteAuthorById(id);
    }

    @GetMapping(path = "/{name}")
    public Author getAuthorByName(@PathVariable String name){
        return authorService.getAuthorsByName(name);
    }

}
