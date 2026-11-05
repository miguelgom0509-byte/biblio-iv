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

    /**
     * Valida y guarda. Si los datos no son válidos lanza IllegalArgumentException
     * con un mensaje claro (el GlobalExceptionHandler lo convierte en un 400 con ese mensaje).
     * Devuelve null si se intenta actualizar un libro que no existe (el controller responde 404).
     */
    public Libro guardarLibro(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (libro.getAutor() == null || libro.getAutor().isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (libro.getAnio() < 0) {
            throw new IllegalArgumentException("El año no puede ser negativo");
        }
        if (libro.getStock() != null && libro.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return libroRepository.guardar(libro);
    }

    public void eliminarLibro(int id) {
        libroRepository.eliminar(id);
    }
}