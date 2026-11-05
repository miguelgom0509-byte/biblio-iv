package pe.edu.iesptecnojuliaca.biblio.repository;

import org.springframework.stereotype.Repository;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class EstudianteRepository {

    private final List<Estudiante> estudiantes = new CopyOnWriteArrayList<>();

    public EstudianteRepository() {
        // Estudiantes iniciales (cámbialos por los tuyos si lo necesitas)
        estudiantes.add(new Estudiante(1, "Juan Pérez", "Desarrollo de Sistemas de Información"));
        estudiantes.add(new Estudiante(2, "María Quispe", "Desarrollo de Sistemas de Información"));
        estudiantes.add(new Estudiante(3, "Carlos Mamani", "Desarrollo de Sistemas de Información"));
    }

    public List<Estudiante> listar() {
        return new ArrayList<>(estudiantes);
    }

    public Estudiante buscarPorId(int id) {
        return estudiantes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    /**
     * Agrega un estudiante nuevo con el siguiente id disponible.
     * Lanza IllegalArgumentException (el GlobalExceptionHandler la convierte en un 400 con mensaje)
     * si el nombre es muy corto o ya existe.
     */
    public synchronized Estudiante guardar(Estudiante estudiante) {
        String nombre = estudiante.getNombre() == null ? "" : estudiante.getNombre().trim().replaceAll("\\s+", " ");
        if (nombre.length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }
        boolean duplicado = estudiantes.stream().anyMatch(e -> e.getNombre() != null && e.getNombre().equalsIgnoreCase(nombre));
        if (duplicado) {
            throw new IllegalArgumentException("Ya existe un estudiante con ese nombre");
        }

        String carrera = estudiante.getCarrera() == null ? "" : estudiante.getCarrera().trim();

        Estudiante nuevo = new Estudiante();
        nuevo.setId(estudiantes.stream().mapToInt(Estudiante::getId).max().orElse(0) + 1);
        nuevo.setNombre(nombre);
        nuevo.setCarrera(carrera.isEmpty() ? null : carrera);
        estudiantes.add(nuevo);
        return nuevo;
    }
}