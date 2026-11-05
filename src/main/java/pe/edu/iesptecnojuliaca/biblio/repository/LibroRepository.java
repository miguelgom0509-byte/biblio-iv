package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class LibroRepository {

    private static final int STOCK_INICIAL = 50;

    private final List<Libro> libros = new CopyOnWriteArrayList<>();

    public LibroRepository() {
        libros.add(new Libro(1, "Java desde cero", "Carlos Pérez", "Programación", 2026, STOCK_INICIAL));
        libros.add(new Libro(2, "Spring Boot básico", "Ana Torres", "Desarrollo Web", 2025, STOCK_INICIAL));
        libros.add(new Libro(3, "Testing de Software", "Luis Ramos", "Pruebas de Software", 2024, STOCK_INICIAL));
    }

    public List<Libro> listar() {
        // Copia de la lista (los objetos Libro siguen siendo los mismos)
        return new ArrayList<>(libros);
    }

    public Libro buscarPorId(int id) {
        return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    /**
     * Crea (id == 0) o actualiza un libro.
     * - Crear: stock null -> 50; un stock 0 explícito se respeta.
     * - Actualizar: solo cambia titulo, autor, categoria y anio.
     *   El stock NO se toca (cambia solo por préstamos/devoluciones).
     * Devuelve null si el id a actualizar no existe (el controller debe responder 404).
     */
    public synchronized Libro guardar(Libro libro) {
        if (libro.getId() == 0) {
            int nuevoId = libros.stream().mapToInt(Libro::getId).max().orElse(0) + 1;
            libro.setId(nuevoId);
            if (libro.getStock() == null) libro.setStock(STOCK_INICIAL);
            libros.add(libro);
            return libro;
        }

        Libro existente = buscarPorId(libro.getId());
        if (existente == null) return null;

        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setCategoria(libro.getCategoria());
        existente.setAnio(libro.getAnio());
        return existente;
    }

    /** Devuelve true si el libro existía y fue eliminado. */
    public boolean eliminar(int id) {
        return libros.removeIf(libro -> libro.getId() == id);
    }
}