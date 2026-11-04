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

    public boolean evaluarPrestamo(int idEstudiante, int idLibro) {
        Libro libro = libroRepository.buscarPorId(idLibro);

        // Regla 1: El libro debe existir y tener stock
        if (libro == null || libro.getStock() <= 0) {
            return false;
        }

        // Regla 2: (Opcional) Validar que el estudiante no tenga muchos préstamos.
        // Por ahora, si hay stock, autorizamos y restamos 1.
        libro.setStock(libro.getStock() - 1);
        return true;
    }
}