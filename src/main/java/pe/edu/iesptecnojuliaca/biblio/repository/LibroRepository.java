package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    private final List<Libro> libros = new ArrayList<>();

    public LibroRepository() {
        // Aquí está el 50 de stock inicial
        libros.add(new Libro(1, "Java desde cero", "Carlos Pérez", "Programación", 2026, 50));
        libros.add(new Libro(2, "Spring Boot básico", "Ana Torres", "Desarrollo Web", 2025, 50));
        libros.add(new Libro(3, "Testing de Software", "Luis Ramos", "Pruebas de Software", 2024, 50));
    }

    public List<Libro> listar() {
        return libros;
    }

    public Libro buscarPorId(int id) {
        return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    public Libro guardar(Libro libro) {
        if (libro.getId() == 0) {
            // Es un libro nuevo
            int nuevoId = libros.stream().mapToInt(Libro::getId).max().orElse(0) + 1;
            libro.setId(nuevoId);
            if (libro.getStock() == 0) libro.setStock(50); // Si no le pones stock, se pone 50
            libros.add(libro);
        } else {
            // Es una EDICIÓN (El lápiz)
            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getId() == libro.getId()) {
                    libros.set(i, libro); // Reemplaza el libro viejo por el nuevo con el stock cambiado
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