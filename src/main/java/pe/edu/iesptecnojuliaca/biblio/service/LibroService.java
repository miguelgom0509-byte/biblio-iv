package pe.edu.iesptecnojuliaca.biblio.service;

import org.springframework.stereotype.Service;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.listar();
    }

    public Libro obtenerLibro(int id) {
        return libroRepository.buscarPorId(id);
    }

    /*
    private final List<Libro> libros = List.of(
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

    public List<Libro> listarLibros() {
        return libros;
    }

    public Libro obtenerLibro(int id) {
        return libros.get(id - 1);
    }

    */
}
