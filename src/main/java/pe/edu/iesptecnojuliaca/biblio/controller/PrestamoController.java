package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*") // Permite que tu HTML haga peticiones a este controlador
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/evaluar")
    public ResponseEntity<Map<String, Object>> evaluarPrestamo(
            @RequestParam Long idEstudiante,
            @RequestParam int stock,
            @RequestParam int prestamosActivos) {

        boolean autorizado = prestamoService.evaluarPrestamoPorId(idEstudiante, stock, prestamosActivos);

        // Creamos un JSON para responder de forma más profesional al Frontend
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("autorizado", autorizado);

        if (autorizado) {
            respuesta.put("mensaje", "✅ Préstamo AUTORIZADO");
            return ResponseEntity.ok(respuesta);
        } else {
            respuesta.put("mensaje", "❌ Préstamo DENEGADO. Verifique el stock o sus préstamos activos.");
            // Devolvemos 200 OK pero con el estado false, para que el JS sepa manejarlo
            return ResponseEntity.ok(respuesta);
        }
    }
}