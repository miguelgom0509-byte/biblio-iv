package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.model.LibroRequest;
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroService.listarLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtener(@PathVariable int id) {
        Libro libro = libroService.obtenerLibro(id);
        return libro != null ? ResponseEntity.ok(libro) : ResponseEntity.notFound().build();
    }

    // Crear: siempre id 0 (el repositorio asigna el nuevo id)
    @PostMapping
    public ResponseEntity<Libro> guardar(@RequestBody LibroRequest request) {
        return ResponseEntity.ok(libroService.guardarLibro(request.aLibro(0)));
    }

    // Editar (lápiz): usa el id de la URL y responde 404 si no existe
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable int id, @RequestBody LibroRequest request) {
        Libro actualizado = libroService.guardarLibro(request.aLibro(id));
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}