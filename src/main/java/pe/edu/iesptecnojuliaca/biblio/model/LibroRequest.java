package pe.edu.iesptecnojuliaca.biblio.model;

/**
 * Datos que envía el navegador al crear o editar un libro.
 * Usa Integer (no int) para que un número ausente o null NO provoque un error 400.
 * El id nunca viene en el JSON: lo decide el controller (0 = nuevo, o el id de la URL).
 */
public record LibroRequest(
        String titulo,
        String autor,
        String categoria,
        Integer anio,
        Integer stock
) {
    public Libro aLibro(int id) {
        Libro libro = new Libro();
        libro.setId(id);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setAnio(anio != null ? anio : 0);
        libro.setStock(stock); // null = "no enviado" (se conserva el actual al editar, 50 al crear)
        return libro;
    }
}