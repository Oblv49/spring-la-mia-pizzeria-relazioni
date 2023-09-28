package org.java.lessons.lamiapizzeriacrud.repository;

import org.java.lessons.lamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzeRepository extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNameContaining(String searchString);

}
