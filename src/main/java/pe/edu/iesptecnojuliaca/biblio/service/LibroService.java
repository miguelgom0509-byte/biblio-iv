package pe.edu.iesptecnojuliaca.biblio.service;

import org.springframework.stereotype.Service;
import pe.edu.iesptecnojuliaca.biblio.model.Libro;
import pe.edu.iesptecnojuliaca.biblio.repository.LibroRepository;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.listar();
    }

    public Libro obtenerLibro(int id) {
        return libroRepository.buscarPorId(id);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.guardar(libro);
    }

    public void eliminarLibro(int id) {
        libroRepository.eliminar(id);
    }
}