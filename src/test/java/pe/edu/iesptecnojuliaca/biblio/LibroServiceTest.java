package pe.edu.iesptecnojuliaca.biblio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.service.LibroService;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;

public class LibroServiceTest {

    @Test
    void obtenerlibroporid(){
        LibroRepository lr= new LibroRepository();
        LibroService ls=new LibroService(lr);

        Libro resultado=ls.obtenerLibro(2);

        assertEquals("Spring Boot básico",resultado.getTitulo());
    }

}
