package org.java.project.final_project.controller;

import java.util.List;

import org.java.project.final_project.model.Genre;
import org.java.project.final_project.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        return "genres/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "genres/create";
        }

        genreRepository.save(formGenre);
        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        genreRepository.deleteById(id);

        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id).get());
        return "genres/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genre") Genre formGenre,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/edit";
        }

        genreRepository.save(formGenre);
        return "redirect:/genres";
    }

}
