package org.java.project.final_project.repository;

import org.java.project.final_project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
    
}
