package pe.edu.iesptecnojuliaca.biblio.model;

/**
 * Datos que envía el navegador al agregar un estudiante.
 * El id nunca viene en el JSON: lo asigna el repositorio.
 */
public record EstudianteRequest(
        String nombre,
        String carrera
) {
    public Estudiante aEstudiante() {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setCarrera(carrera);
        return estudiante;
    }
}