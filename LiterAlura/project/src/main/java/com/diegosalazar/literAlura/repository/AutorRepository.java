package com.diegosalazar.literAlura.repository;

import com.diegosalazar.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.anioNacimiento AND COALESCE(a.anioFallecimiento, 2024)")
    List<Autor> findAutoresVivosPorAnio(int anio);
}