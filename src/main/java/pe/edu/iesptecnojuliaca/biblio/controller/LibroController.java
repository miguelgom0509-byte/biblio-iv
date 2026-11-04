package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
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

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }

    // ESTO ES LO QUE HACE QUE EL LÁPIZ DE EDITAR FUNCIONE
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable int id, @RequestBody Libro libro) {
        libro.setId(id); // Nos aseguramos de que mantenga el mismo ID
        return libroService.guardarLibro(libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}