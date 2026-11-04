package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    // 1. Usamos ArrayList (mutable) en lugar de List.of (inmutable)
    // Esto permite agregar y eliminar libros.
    private final List<Libro> libros = new ArrayList<>();

    // 2. Inicializamos los datos en el constructor asignando IDs manuales
    public LibroRepository() {
        libros.add(new Libro(1, "Java desde cero", "Carlos Pérez", "Programación", 2026));
        libros.add(new Libro(2, "Spring Boot básico", "Ana Torres", "Desarrollo Web", 2025));
        libros.add(new Libro(3, "Testing de Software", "Luis Ramos", "Pruebas de Software", 2024));
    }

    // READ: Listar todos
    public List<Libro> listar() {
        return libros;
    }

    // READ: Buscar por ID usando Streams (más seguro que calcular posiciones)
    public Libro buscarPorId(int id) {
        return libros.stream()
                .filter(libro -> libro.getId() == id)
                .findFirst()
                .orElse(null); // Retorna null si no lo encuentra
    }

    // CREATE: Guardar un nuevo libro (Llamado desde el Service en el POST)
    public Libro guardar(Libro libro) {
        // Si el ID es 0, significa que es un libro nuevo desde el HTML
        if (libro.getId() == 0) {
            // Generamos un ID autoincremental buscando el ID más alto actual
            int nuevoId = libros.stream()
                    .mapToInt(Libro::getId)
                    .max()
                    .orElse(0) + 1;
            libro.setId(nuevoId);
            libros.add(libro);
        } else {
            // Si ya tiene ID, actualizamos el libro existente
            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getId() == libro.getId()) {
                    libros.set(i, libro);
                    break;
                }
            }
        }
        return libro;
    }

    // DELETE: Eliminar un libro (Llamado desde el Service en el DELETE)
    public void eliminar(int id) {
        // Elimina de la lista cualquier libro que coincida con el ID
        libros.removeIf(libro -> libro.getId() == id);
    }
}