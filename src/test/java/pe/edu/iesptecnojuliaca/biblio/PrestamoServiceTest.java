package pe.edu.iesptecnojuliaca.biblio;
import org.junit.jupiter.api.Test;
import pe.edu.iesptecnojuliaca.biblio.repository.EstudianteRepository;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoServiceTest {

    // CP01: No hay stock (Ejemplo del PDF)
    @Test
    void noDebePrestarSinStock() {
        // Arrange
        EstudianteRepository repo = new EstudianteRepository();
        PrestamoService servicio = new PrestamoService(repo);

        // Act
        boolean resultado = servicio.puedePrestar(0, true, 0);

        // Assert
        assertFalse(resultado, "Debería retornar false porque no hay stock");
    }

    // CP02: Lector inactivo
    @Test
    void noDebePrestarSiLectorInactivo() {
        // Arrange
        EstudianteRepository repo = new EstudianteRepository();
        PrestamoService servicio = new PrestamoService(repo);

        // Act
        boolean resultado = servicio.puedePrestar(1, false, 0);

        // Assert
        assertFalse(resultado, "Debería retornar false porque el lector no está activo");
    }

    // CP03: Límite de préstamos alcanzado (3 o más)
    @Test
    void noDebePrestarSiTieneTresPrestamosActivos() {
        // Arrange
        EstudianteRepository repo = new EstudianteRepository();
        PrestamoService servicio = new PrestamoService(repo);

        // Act
        boolean resultado = servicio.puedePrestar(1, true, 3);

        // Assert
        assertFalse(resultado, "Debería retornar false porque ya tiene 3 préstamos");
    }

    // CP04: Préstamo exitoso (Todas las condiciones se cumplen)
    @Test
    void debePrestarSiCumpleTodasLasCondiciones() {
        // Arrange
        EstudianteRepository repo = new EstudianteRepository();
        PrestamoService servicio = new PrestamoService(repo);

        // Act
        boolean resultado = servicio.puedePrestar(1, true, 0);

        // Assert
        assertTrue(resultado, "Debería retornar true porque todo está correcto");
    }

    // CP05: Límite inferior de préstamos (2 activos)
    @Test
    void debePrestarSiTieneDosPrestamosActivos() {
        // Arrange
        EstudianteRepository repo = new EstudianteRepository();
        PrestamoService servicio = new PrestamoService(repo);

        // Act
        boolean resultado = servicio.puedePrestar(1, true, 2);

        // Assert
        assertTrue(resultado, "Debería retornar true porque tiene menos de 3 préstamos");
    }
}