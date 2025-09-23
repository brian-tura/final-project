package org.java.project.final_project.controller;

import java.util.List;

import org.java.project.final_project.model.Movie;
import org.java.project.final_project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> index() {
        
        List<Movie> movies = movieService.findAll();
        return movies;
    }

    @GetMapping("/{id}")
    public Movie show(@PathVariable("id") Integer id) {
        Movie movie = movieService.findById(id);
        return movie;
    }

    @PostMapping("/create")
    public Movie store(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@RequestBody Movie movie, @PathVariable("id") Integer id) {
        movie.setId(id);
        return movieService.update(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        
        movieService.deleteById(id);
    }



}
