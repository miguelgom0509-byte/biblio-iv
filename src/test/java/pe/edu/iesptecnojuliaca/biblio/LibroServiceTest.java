package pe.edu.iesptecnojuliaca.biblio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;

import static org.junit.jupiter.api.Assertions.*;

public class LibroServiceTest {

    private LibroService ls;

    @BeforeEach
    void setUp() {
        ls = new LibroService(new LibroRepository());
    }

    private Libro libro(int id, String titulo, String autor, Integer stock) {
        Libro l = new Libro();
        l.setId(id);
        l.setTitulo(titulo);
        l.setAutor(autor);
        l.setCategoria("Programación");
        l.setAnio(2026);
        l.setStock(stock);
        return l;
    }

    @Test
    @DisplayName("Obtiene un libro por id")
    void obtenerlibroporid() {
        Libro resultado = ls.obtenerLibro(2);
        assertEquals("Spring Boot básico", resultado.getTitulo());
    }

    @Test
    @DisplayName("Editar un libro actualiza sus datos y su stock")
    void editarActualizaDatosYStock() {
        ls.guardarLibro(libro(1, "Java desde cero 2da ed.", "Carlos Pérez", 30));

        Libro editado = ls.obtenerLibro(1);
        assertEquals("Java desde cero 2da ed.", editado.getTitulo());
        assertEquals(30, editado.getStock().intValue());
    }

    @Test
    @DisplayName("Editar sin enviar stock conserva el stock actual")
    void editarSinStockConservaElActual() {
        ls.guardarLibro(libro(1, "Java desde cero", "Carlos Pérez", null));

        assertEquals(50, ls.obtenerLibro(1).getStock().intValue());
    }

    @Test
    @DisplayName("Editar un libro inexistente devuelve null")
    void editarLibroInexistenteDevuelveNull() {
        assertNull(ls.guardarLibro(libro(99, "Fantasma", "Nadie", 5)));
    }

    @Test
    @DisplayName("Crear un libro con stock 0 respeta el 0")
    void crearConStockCeroRespetaElCero() {
        Libro creado = ls.guardarLibro(libro(0, "Libro agotado", "Autor", 0));

        assertTrue(creado.getId() > 0);
        assertEquals(0, creado.getStock().intValue());
    }

    @Test
    @DisplayName("Crear un libro sin stock le asigna 50")
    void crearSinStockAsigna50() {
        Libro creado = ls.guardarLibro(libro(0, "Libro nuevo", "Autor", null));

        assertEquals(50, creado.getStock().intValue());
    }

    @Test
    @DisplayName("Un stock negativo lanza error de validación")
    void stockNegativoLanzaError() {
        assertThrows(IllegalArgumentException.class,
                () -> ls.guardarLibro(libro(1, "Java desde cero", "Carlos Pérez", -5)));
    }

    @Test
    @DisplayName("Título o autor vacíos lanzan error de validación")
    void tituloOAutorVaciosLanzanError() {
        assertThrows(IllegalArgumentException.class,
                () -> ls.guardarLibro(libro(1, " ", "Carlos Pérez", 10)));
        assertThrows(IllegalArgumentException.class,
                () -> ls.guardarLibro(libro(1, "Java desde cero", "", 10)));
    }
}