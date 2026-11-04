package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EstudianteRepository {

    // Usamos ArrayList para permitir agregar o eliminar estudiantes en el futuro
    private final List<Estudiante> estudiantes = new ArrayList<>();

    // Inicializamos los datos en el constructor
    public EstudianteRepository() {
        estudiantes.add(new Estudiante(1L, "Juan Perez", true));   // Estudiante Activo
        estudiantes.add(new Estudiante(2L, "Maria Lopez", false)); // Estudiante Inactivo
        estudiantes.add(new Estudiante(3L, "Carlos Ruiz", true));  // Estudiante Activo
    }

    public List<Estudiante> listar() {
        return estudiantes;
    }

    // Usamos Optional para manejar de forma segura la búsqueda por ID
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    // Método extra para guardar (por si lo necesitas después)
    public Estudiante guardar(Estudiante estudiante) {
        if (estudiante.getId() == null) {
            // Generar ID autoincremental
            Long nuevoId = estudiantes.stream().mapToLong(Estudiante::getId).max().orElse(0L) + 1;
            estudiante.setId(nuevoId);
            estudiantes.add(estudiante);
        }
        return estudiante;
    }
}