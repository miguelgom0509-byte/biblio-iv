package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.iesptecnojuliaca.biblio.model.Estudiante;
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
        // Retorna la lista de estudiantes que tienes en tu repositorio
        return estudianteRepository.listar();
    }
}