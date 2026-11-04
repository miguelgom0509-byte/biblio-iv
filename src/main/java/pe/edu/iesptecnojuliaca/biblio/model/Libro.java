package pe.edu.iesptecnojuliaca.biblio.model;

public class Libro {

    // 1. Agregamos el ID (Fundamental para la base de datos y la tabla HTML)
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;

    // 2. Constructor VACÍO (Obligatorio para que Spring/Jackson pueda convertir el JSON de entrada)
    public Libro() {
    }

    // 3. Constructor con todos los campos (Útil para tus pruebas o para crear datos de ejemplo)
    public Libro(int id, String titulo, String autor, String categoria, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
    }

    // 4. Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}