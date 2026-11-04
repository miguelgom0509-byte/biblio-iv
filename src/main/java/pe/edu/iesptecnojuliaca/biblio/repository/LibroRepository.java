package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    // Usamos ArrayList para poder agregar y eliminar dinámicamente
    private final List<Libro> libros = new ArrayList<>();

    public LibroRepository() {
        // Datos iniciales de prueba
        libros.add(new Libro(1, "Java desde cero", "Carlos Pérez", "Programación", 2026));
        libros.add(new Libro(2, "Spring Boot básico", "Ana Torres", "Desarrollo Web", 2025));
        libros.add(new Libro(3, "Testing de Software", "Luis Ramos", "Pruebas de Software", 2024));
    }

    public List<Libro> listar() {
        return libros;
    }

    public Libro buscarPorId(int id) {
        return libros.stream()
                .filter(libro -> libro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Libro guardar(Libro libro) {
        if (libro.getId() == 0) {
            // Generar nuevo ID autoincremental
            int nuevoId = libros.stream().mapToInt(Libro::getId).max().orElse(0) + 1;
            libro.setId(nuevoId);
            libros.add(libro);
        } else {
            // Actualizar existente (por si se necesita a futuro)
            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getId() == libro.getId()) {
                    libros.set(i, libro);
                    break;
                }
            }
        }
        return libro;
    }

    public void eliminar(int id) {
        libros.removeIf(libro -> libro.getId() == id);
    }
}