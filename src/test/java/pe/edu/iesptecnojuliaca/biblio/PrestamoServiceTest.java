package pe.edu.iesptecnojuliaca.biblio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoServiceTest {

    private LibroRepository libroRepository;
    private PrestamoService prestamoService;

    // Este método se ejecuta ANTES de cada prueba para tener todo limpio
    @BeforeEach
    void setUp() {
        // Usamos el repositorio real (que ya viene con los 3 libros y stock 50)
        libroRepository = new LibroRepository();
        prestamoService = new PrestamoService(libroRepository);
    }

    // CP01: Préstamo exitoso (El libro tiene stock)
    @Test
    @DisplayName("Debe autorizar el préstamo si hay stock disponible")
    void debeAutorizarPrestamoConStock() {
        // Arrange (Preparar)
        int idEstudiante = 1;
        int idLibro = 1; // El libro "Java desde cero" tiene stock 50

        // Act (Actuar)
        boolean resultado = prestamoService.evaluarPrestamo(idEstudiante, idLibro);

        // Assert (Afirmar/Verificar)
        assertTrue(resultado, "El préstamo debería ser autorizado porque hay stock");

        // Verificamos que el stock haya bajado de 50 a 49
        Libro libroModificado = libroRepository.buscarPorId(idLibro);
        assertEquals(49, libroModificado.getStock(), "El stock debió reducirse en 1");
    }

    // CP02: Préstamo denegado (El libro NO tiene stock)
    @Test
    @DisplayName("Debe denegar el préstamo si el stock es cero")
    void noDebeAutorizarPrestamoSinStock() {
        // Arrange
        int idEstudiante = 1;
        int idLibro = 2; // Vamos a usar el libro 2

        // Forzamos el stock a 0 para la prueba
        Libro libroSinStock = libroRepository.buscarPorId(idLibro);
        libroSinStock.setStock(0);

        // Act
        boolean resultado = prestamoService.evaluarPrestamo(idEstudiante, idLibro);

        // Assert
        assertFalse(resultado, "El préstamo debería ser denegado porque no hay stock");
        assertEquals(0, libroSinStock.getStock(), "El stock debe seguir siendo 0");
    }

    // CP03: Préstamo denegado (El libro no existe)
    @Test
    @DisplayName("Debe denegar el préstamo si el libro no existe")
    void noDebeAutorizarPrestamoSiLibroNoExiste() {
        // Arrange
        int idEstudiante = 1;
        int idLibroInexistente = 99; // Este ID no está en el repositorio

        // Act
        boolean resultado = prestamoService.evaluarPrestamo(idEstudiante, idLibroInexistente);

        // Assert
        assertFalse(resultado, "El préstamo debería ser denegado porque el libro no existe");
    }
}