package pe.edu.iesptecnojuliaca.biblio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;
import pe.edu.iesptecnojuliaca.biblio.service.PrestamoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoServiceTest {

    private LibroRepository libroRepositoryMock;
    private PrestamoService servicio;

    @BeforeEach
    void setUp() {
        // Creamos un repositorio falso (Mock) para no usar los datos reales de la base de datos
        libroRepositoryMock = mock(LibroRepository.class);
        // Le pasamos el repositorio falso al servicio
        servicio = new PrestamoService(libroRepositoryMock);
    }

    // CP01: No hay stock (El libro tiene stock 0)
    @Test
    void noDebePrestarSinStock() {
        // Arrange: Creamos un libro con stock 0 y decimos que cuando busquen el ID 1, devuelva ese libro
        Libro libroSinStock = new Libro(1, "Java desde cero", "Carlos", "Prog", 2026, 0);
        when(libroRepositoryMock.buscarPorId(1)).thenReturn(libroSinStock);

        // Act: Evaluamos el préstamo del estudiante 1 con el libro 1
        boolean resultado = servicio.evaluarPrestamo(1, 1);

        // Assert: Debería denegar el préstamo
        assertFalse(resultado, "Debería retornar false porque no hay stock");
    }

    // CP02: Préstamo exitoso (Hay stock disponible)
    @Test
    void debePrestarSiHayStockDisponible() {
        // Arrange: Creamos un libro con stock 10
        Libro libroConStock = new Libro(2, "Spring Boot", "Ana", "Web", 2025, 10);
        when(libroRepositoryMock.buscarPorId(2)).thenReturn(libroConStock);

        // Act
        boolean resultado = servicio.evaluarPrestamo(1, 2);

        // Assert: Debería autorizar el préstamo
        assertTrue(resultado, "Debería retornar true porque hay stock");
    }

    // CP03: El stock disminuye correctamente tras un préstamo exitoso
    @Test
    void debeReducirElStockAlPrestar() {
        // Arrange: Libro con stock 5
        Libro libro = new Libro(3, "Testing", "Luis", "Pruebas", 2024, 5);
        when(libroRepositoryMock.buscarPorId(3)).thenReturn(libro);

        // Act
        servicio.evaluarPrestamo(1, 3);

        // Assert: El stock debe haber bajado a 4
        assertEquals(4, libro.getStock(), "El stock debería haber disminuido en 1");
    }

    // CP04: Libro no encontrado
    @Test
    void noDebePrestarSiElLibroNoExiste() {
        // Arrange: Cuando busquen el ID 99, devolvemos null
        when(libroRepositoryMock.buscarPorId(99)).thenReturn(null);

        // Act
        boolean resultado = servicio.evaluarPrestamo(1, 99);

        // Assert
        assertFalse(resultado, "Debería retornar false porque el libro no existe");
    }
}