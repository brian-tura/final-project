package org.java.project.final_project.repository;

import org.java.project.final_project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
    
}
