package pe.edu.iesptecnojuliaca.biblio.service;

import org.springframework.stereotype.Service;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;

@Service
public class PrestamoService {

    private final LibroRepository libroRepository;

    public PrestamoService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Ahora solo pedimos los IDs, el backend hace el resto
    public boolean evaluarPrestamo(int idEstudiante, int idLibro) {
        Libro libro = libroRepository.buscarPorId(idLibro);

        // Regla 1: El libro debe existir
        if (libro == null) return false;

        // Regla 2: Debe haber stock disponible
        if (libro.getStock() <= 0) return false;

        // (Aquí podrías agregar la validación de que el estudiante no tenga más de 3 préstamos)

        // Si todo está OK, reducimos el stock y autorizamos
        libro.setStock(libro.getStock() - 1);
        return true;
    }
}