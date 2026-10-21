package pe.edu.iesptecnojuliaca.biblio.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/evaluar")
    public ResponseEntity<String> evaluarPrestamo(
            @RequestParam Long idEstudiante,
            @RequestParam int stock,
            @RequestParam int prestamosActivos) {

        boolean autorizado = prestamoService.evaluarPrestamoPorId(idEstudiante, stock, prestamosActivos);

        if (autorizado) {
            return ResponseEntity.ok("✅ Préstamo AUTORIZADO");
        } else {
            return ResponseEntity.badRequest().body("❌ Préstamo DENEGADO");
        }
    }
}
