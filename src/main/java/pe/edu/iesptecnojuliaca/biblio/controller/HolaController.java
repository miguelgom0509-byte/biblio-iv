package pe.edu.iesptecnojuliaca.biblio.controller;

import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Trabajando con servicios
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;

@RestController
public class HolaController {

    private final LibroService libroService;

    public HolaController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public List<Libro> libros() {
        return libroService.listarLibros();
    }

    @GetMapping("/libros/{id}")
    public Libro obtenerLibro(@PathVariable int id) {
        return libroService.obtenerLibro(id);
    }

/*
    @GetMapping("/hola")
    public String hola() {
        return "Mi primer metodo API, con SpringBoot";
    }

    @GetMapping("/curso")
    public String curso() {
        return "Testing y Automatización de Pruebas Funcionales";
    }

    @GetMapping("/estudiante/datos")
    public Estudiante datosEstudiante() {
        Estudiante estudiante = new Estudiante(
                "Juan Pérez",
                "Desarrollo de Sistemas de Información",
                3
        );
        return estudiante;
    }

    @GetMapping("/libro")
    public Libro libro() {
        Libro libro = new Libro(
                "Java desde cero",
                "Carlos Pérez",
                "Programación",
                2026
        );
        return libro;
    }

    @GetMapping("/libros")
    public List<Libro> libros() {
        return List.of(
                new Libro(
                        "Java desde cero",
                        "Carlos Pérez",
                        "Programación",
                        2026
                ),
                new Libro(
                        "Spring Boot básico",
                        "Ana Torres",
                        "Desarrollo Web",
                        2025
                ),
                new Libro(
                        "Testing de Software",
                        "Luis Ramos",
                        "Pruebas de Software",
                        2024
                )
        );
    }

    // Inicio Metodo RequestParam
    @GetMapping("/saludo")
    public String saludo(@RequestParam String nombre) {
        return "Hola " + nombre + ", bienvenido a Spring Boot";
    }

    @GetMapping("/bienvenida")
    public String bienvenida(@RequestParam(defaultValue = "Estudiante") String nombre) {
        return "Bienvenido " + nombre;
    }
    // Termina Metodo RequestParam


    @GetMapping("/libro/{id}")
    public String buscarLibro(@PathVariable int id) {
        return "Buscando libro con ID: " + id;
    }

    @GetMapping("/libros/{id}")
    public Libro obtenerLibro(@PathVariable int id) {
        List<Libro> libros = List.of(
                new Libro(
                        "Java desde cero",
                        "Carlos Pérez",
                        "Programación",
                        2026
                ),
                new Libro(
                        "Spring Boot básico",
                        "Ana Torres",
                        "Desarrollo Web",
                        2025
                ),
                new Libro(
                        "Testing de Software",
                        "Luis Ramos",
                        "Pruebas de Software",
                        2024
                )
        );
        return libros.get(id - 1);
    }
*/

}
