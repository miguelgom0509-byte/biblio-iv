package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;
import pe.edu.iesptecnojuliaca.biblio.model.EstudianteRequest;
import pe.edu.iesptecnojuliaca.biblio.repository.EstudianteRepository;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping
    public List<Estudiante> listar() {
        return estudianteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable int id) {
        Estudiante estudiante = estudianteRepository.buscarPorId(id);
        return estudiante != null ? ResponseEntity.ok(estudiante) : ResponseEntity.notFound().build();
    }

    // Agregar estudiante: responde 201 con el estudiante creado (incluye su nuevo id)
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody EstudianteRequest request) {
        Estudiante nuevo = estudianteRepository.guardar(request.aEstudiante());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}