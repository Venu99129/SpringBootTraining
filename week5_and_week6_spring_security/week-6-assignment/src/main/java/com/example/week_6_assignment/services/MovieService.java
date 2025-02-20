package com.example.week_6_assignment.services;

import com.example.week_6_assignment.entites.Movie;
import com.example.week_6_assignment.exceptions.ResourceNotFoundException;
import com.example.week_6_assignment.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Create a new movie
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update an existing movie
    public Movie updateMovie(Long movieId, Movie movie) {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("move not found with this movieId :" + movieId));

        modelMapper.map(existingMovie,movie);
          // Ensure the movie ID is set to the correct one
        return movieRepository.save(existingMovie);

    }

    // Delete a movie by ID
    public void deleteMovie(Long movieId) {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("move not found with this movieId :" + movieId));

        movieRepository.delete(existingMovie);
    }
}

