package com.JapDetailed.week3_assignment.services;

import com.JapDetailed.week3_assignment.entities.Author;
import com.JapDetailed.week3_assignment.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors(Long id) {
        if(id != null) return List.of(authorRepository.findById(id).get());
        else return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Boolean deleteAuthorById(Long id) {
        if (authorRepository.existsById(id))
            authorRepository.deleteById(id);
        else return false;
        return true;
    }

    public Author getAuthorsByName(String name) {
        return authorRepository.findByName(name);
    }
}
