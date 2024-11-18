package com.diegosalazar.literAlura;

import com.diegosalazar.literAlura.controller.MenuController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MenuController menuController) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== LiterAlura - Catálogo de Libros ===");
                System.out.println("1. Buscar libro por título");
                System.out.println("2. Listar todos los libros");
                System.out.println("3. Filtrar libros por idioma");
                System.out.println("4. Listar todos los autores");
                System.out.println("5. Buscar autores vivos en un año específico");
                System.out.println("6. Ver estadísticas");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1 -> menuController.buscarLibroPorTitulo(scanner);
                    case 2 -> menuController.listarTodosLosLibros();
                    case 3 -> menuController.filtrarLibrosPorIdioma(scanner);
                    case 4 -> menuController.listarTodosLosAutores();
                    case 5 -> menuController.buscarAutoresVivosPorAnio(scanner);
                    case 6 -> menuController.mostrarEstadisticas();
                    case 0 -> continuar = false;
                    default -> System.out.println("Opción no válida");
                }
            }
            scanner.close();
        };
    }
}