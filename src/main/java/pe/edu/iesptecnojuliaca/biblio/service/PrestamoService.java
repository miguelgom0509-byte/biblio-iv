package pe.edu.iesptecnojuliaca.biblio.service;
import org.springframework.stereotype.Service;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;
import pe.edu.iesptecnojuliaca.biblio.repository.EstudianteRepository;

import java.util.Optional;

@Service
public class PrestamoService {

    private final EstudianteRepository estudianteRepository;

    // Inyectamos el repositorio
    public PrestamoService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // --- TU MÉTODO ORIGINAL (No cambia, tus pruebas JUnit siguen pasando) ---
    public boolean puedePrestar(int stock, boolean lectorActivo, int prestamosActivos) {
        if (stock <= 0) {
            return false;
        }
        if (!lectorActivo) {
            return false;
        }
        if (prestamosActivos >= 3) {
            return false;
        }
        return true;
    }

    // --- NUEVO MÉTODO QUE VALIDA LA EXISTENCIA ---
    public boolean evaluarPrestamoPorId(Long idEstudiante, int stock, int prestamosActivos) {
        // 1. Buscamos al estudiante en el repositorio
        Optional<Estudiante> estudianteOpt = estudianteRepository.buscarPorId(idEstudiante);

        // 2. Si no existe, denegamos el préstamo
        if (estudianteOpt.isEmpty()) {
            System.out.println("El estudiante con ID " + idEstudiante + " no existe.");
            return false;
        }

        // 3. Si existe, extraemos sus datos y llamamos al método original
        Estudiante estudiante = estudianteOpt.get();
        return puedePrestar(stock, estudiante.isActivo(), prestamosActivos);
    }
}