package pe.edu.iesptecnojuliaca.biblio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping("/evaluar")
    public ResponseEntity<Map<String, Object>> evaluarPrestamo(
            @RequestParam int idEstudiante,
            @RequestParam int idLibro) {

        boolean autorizado = prestamoService.evaluarPrestamo(idEstudiante, idLibro);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("autorizado", autorizado);

        if (autorizado) {
            respuesta.put("mensaje", "✅ Préstamo AUTORIZADO. Stock actualizado.");
            return ResponseEntity.ok(respuesta);
        } else {
            respuesta.put("mensaje", "❌ Préstamo DENEGADO. No hay stock disponible.");
            return ResponseEntity.ok(respuesta);
        }
    }
}