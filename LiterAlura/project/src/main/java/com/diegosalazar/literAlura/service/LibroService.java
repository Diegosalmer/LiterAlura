package com.diegosalazar.literAlura.service;

import com.diegosalazar.literAlura.dto.BookDto;
import com.diegosalazar.literAlura.dto.GutendexResponse;
import com.diegosalazar.literAlura.model.Autor;
import com.diegosalazar.literAlura.model.Libro;
import com.diegosalazar.literAlura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.DoubleSummaryStatistics;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final ApiService apiService;
    private final AutorService autorService;

    public LibroService(LibroRepository libroRepository, ApiService apiService, AutorService autorService) {
        this.libroRepository = libroRepository;
        this.apiService = apiService;
        this.autorService = autorService;
    }

    @Transactional
    public List<Libro> buscarPorTitulo(String titulo) {
        try {
            GutendexResponse response = apiService.buscarLibros(titulo);
            List<Libro> librosEncontrados = new ArrayList<>();
            
            for (BookDto bookDto : response.getResults()) {
                Libro libro = new Libro();
                libro.setTitulo(bookDto.getTitle());
                libro.setIdioma(bookDto.getLanguages().get(0));
                libro.setDescargas(bookDto.getDownload_count());
                
                Set<Autor> autores = new HashSet<>();
                for (Map<String, String> autorDto : bookDto.getAuthors()) {
                    Autor autor = new Autor();
                    autor.setNombre(autorDto.get("name"));
                    autor = autorService.guardar(autor);
                    autores.add(autor);
                }
                libro.setAutores(autores);
                
                librosEncontrados.add(libroRepository.save(libro));
            }
            
            return librosEncontrados;
        } catch (Exception e) {
            System.err.println("Error al buscar libros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> filtrarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    public List<Libro> obtenerTop10Descargas() {
        return libroRepository.findTop10ByOrderByDescargasDesc();
    }

    public DoubleSummaryStatistics obtenerEstadisticasDescargas() {
        return libroRepository.findAll().stream()
                .mapToDouble(libro -> libro.getDescargas().doubleValue())
                .summaryStatistics();
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }
}