package com.diegosalazar.literAlura.repository;

import com.diegosalazar.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByIdiomaIgnoreCase(String idioma);
    List<Libro> findTop10ByOrderByDescargasDesc();
}