package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import java.util.List;

@Repository
public class LibroRepository {

    private final List<Libro> libros = List.of(
            new Libro("Java desde cero", "Carlos Pérez", "Programación", 2026),
            new Libro("Spring Boot básico", "Ana Torres", "Desarrollo Web", 2025),
            new Libro("Testing de Software", "Luis Ramos", "Pruebas de Software", 2024)
    );

    public List<Libro> listar() {
        return libros;
    }

    public Libro buscarPorId(int id) {
        // Validación para evitar errores si el ID no existe
        if (id > 0 && id <= libros.size()) {
            return libros.get(id - 1);
        }
        return null; // Retorna null si no lo encuentra
    }
}
