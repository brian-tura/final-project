package org.java.project.final_project.controller;

import java.util.List;

import org.java.project.final_project.model.Genre;
import org.java.project.final_project.model.Movie;
import org.java.project.final_project.repository.GenreRepository;
import org.java.project.final_project.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public String index(Model model) {
        List<Movie> movies = movieRepository.findAll();

        model.addAttribute("movies", movies);
        return "movies/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Movie movie = movieRepository.findById(id).get();
        List<Genre> genres = movie.getGenres();

        model.addAttribute("movie", movie);
        model.addAttribute("genres", genres);

        return "movies/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genres);

        return "movies/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("movie") Movie forMovie, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "movies/create";
        }

        movieRepository.save(forMovie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("movie", movieRepository.findById(id).get());

        return "movies/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("movie") Movie forMovie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "movies/edit";
        }



        movieRepository.save(forMovie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }
}
