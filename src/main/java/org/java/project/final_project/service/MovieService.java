package org.java.project.final_project.service;

import java.util.List;

import org.java.project.final_project.model.Movie;
import org.java.project.final_project.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return movie;
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(Integer id){
        Movie movie = movieRepository.findById(id).get();

        movieRepository.delete(movie);
    }
}
