package com.diegosalazar.literAlura.controller;

import com.diegosalazar.literAlura.model.Libro;
import com.diegosalazar.literAlura.service.AutorService;
import com.diegosalazar.literAlura.service.LibroService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
public class MenuController {
    private final LibroService libroService;
    private final AutorService autorService;

    public MenuController(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título a buscar: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
            return;
        }

        System.out.println("\nLibros encontrados:");
        libros.forEach(libro -> {
            System.out.println("\nTítulo: " + libro.getTitulo());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Descargas: " + libro.getDescargas());
            System.out.println("Autores: " + libro.getAutores().stream()
                    .map(autor -> autor.getNombre())
                    .collect(Collectors.joining(", ")));
        });
    }

    public void listarTodosLosLibros() {
        List<Libro> libros = libroService.listarTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
            return;
        }
        
        System.out.println("\nListado de todos los libros:");
        libros.forEach(libro -> {
            System.out.println("\nTítulo: " + libro.getTitulo());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Descargas: " + libro.getDescargas());
        });
    }

    public void filtrarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (en, es, fr, etc.): ");
        String idioma = scanner.nextLine();
        List<Libro> libros = libroService.filtrarPorIdioma(idioma);
        
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
            return;
        }

        System.out.println("\nLibros en " + idioma + ":");
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }

    public void listarTodosLosAutores() {
        var autores = autorService.listarTodos();
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
            return;
        }

        System.out.println("\nListado de autores:");
        autores.forEach(autor -> System.out.println(autor.getNombre()));
    }

    public void buscarAutoresVivosPorAnio(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        var autores = autorService.buscarAutoresVivosPorAnio(anio);
        
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año especificado.");
            return;
        }

        System.out.println("\nAutores vivos en " + anio + ":");
        autores.forEach(autor -> System.out.println(autor.getNombre()));
    }

    public void mostrarEstadisticas() {
        var stats = libroService.obtenerEstadisticasDescargas();
        System.out.println("\nEstadísticas de descargas:");
        System.out.printf("Promedio: %.2f%n", stats.getAverage());
        System.out.println("Máximo: " + stats.getMax());
        System.out.println("Mínimo: " + stats.getMin());
        System.out.println("Total: " + stats.getSum());
        System.out.println("Cantidad de libros: " + stats.getCount());
    }
}