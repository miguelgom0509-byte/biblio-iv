package pe.edu.iesptecnojuliaca.biblio.service;

import org.springframework.stereotype.Service;
// import pe.edu.iesptecnojuliaca.biblio.repository.EstudianteRepository;

@Service
public class PrestamoService {

    // Aquí podrías inyectar el EstudianteRepository si necesitas verificar si el estudiante existe o está activo
    // private final EstudianteRepository estudianteRepository;

    public PrestamoService(/* EstudianteRepository estudianteRepository */) {
        // this.estudianteRepository = estudianteRepository;
    }

    public boolean evaluarPrestamoPorId(Long idEstudiante, int stock, int prestamosActivos) {
        // Lógica de negocio:
        // 1. Si no hay stock, denegar
        if (stock <= 0) return false;

        // 2. Si tiene 3 o más préstamos, denegar
        if (prestamosActivos >= 3) return false;

        // 3. (Opcional) Verificar que el estudiante existe y está activo
        // boolean esActivo = estudianteRepository.buscarPorId(idEstudiante)
        //        .map(Estudiante::isActivo)
        //        .orElse(false);
        // if (!esActivo) return false;

        // Si pasa todas las reglas, se autoriza
        return true;
    }
}