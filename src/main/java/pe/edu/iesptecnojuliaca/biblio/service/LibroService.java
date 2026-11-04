package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;

import java.util.List;

@RestController
@RequestMapping("/libros")
@CrossOrigin(origins = "*") // Permite que el HTML se conecte sin problemas de seguridad
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // GET: Obtener todos los libros (Para la tabla principal)
    @GetMapping
    public List<Libro> listar() {
        return libroService.listarLibros();
    }

    // GET: Obtener un libro por ID (Para el buscador)
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtener(@PathVariable int id) {
        Libro libro = libroService.obtenerLibro(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build(); // Retorna error 404 si no existe
    }

    // POST: Guardar un nuevo libro (Para el botón "Agregar Libro" del HTML)
    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        // NOTA: Debes agregar un método libroService.guardarLibro(libro) en tu servicio
        return libroService.guardarLibro(libro);
    }

    // DELETE: Eliminar un libro (Para el botón de la papelera en el HTML)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        // NOTA: Debes agregar un método libroService.eliminarLibro(id) en tu servicio
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}