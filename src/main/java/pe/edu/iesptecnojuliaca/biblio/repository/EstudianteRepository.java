package pe.edu.iesptecnojuliaca.biblio.repository;
import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;

import java.util.List;
import java.util.Optional;

@Repository
public class EstudianteRepository {

    // Simulamos una lista de estudiantes en memoria
    private final List<Estudiante> estudiantes = List.of(
            new Estudiante(1L, "Juan Perez", true),   // Estudiante Activo
            new Estudiante(2L, "Maria Lopez", false), // Estudiante Inactivo
            new Estudiante(3L, "Carlos Ruiz", true)   // Estudiante Activo
    );

    public List<Estudiante> listar() {
        return estudiantes;
    }

    // Usamos Optional para manejar de forma segura la búsqueda por ID
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}
