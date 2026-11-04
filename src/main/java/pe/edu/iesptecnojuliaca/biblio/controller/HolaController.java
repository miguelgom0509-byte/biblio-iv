package pe.edu.iesptecnojuliaca.biblio.controller;

import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class HolaController {

    private final LibroService libroService;

    public HolaController(LibroService libroService) {
        this.libroService = libroService;
    }

    // ⚠️ ESTOS DOS MÉTODOS CHOCAN CON LibroController.
    // Los comento para que Spring Boot no se caiga.
    /*
    @GetMapping("/libros")
    public List<Libro> libros() {
        return libroService.listarLibros();
    }

    @GetMapping("/libros/{id}")
    public Libro obtenerLibro(@PathVariable int id) {
        return libroService.obtenerLibro(id);
    }
    */

    // ---------------------------------------------------------
    // Aquí abajo puedes dejar tus métodos de prueba comentados
    // o descomentar solo los que NO se llamen /libros
    // ---------------------------------------------------------

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
    */
}