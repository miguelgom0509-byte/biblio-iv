package pe.edu.iesptecnojuliaca.biblio.model;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;

    public Libro(String titulo, String autor, String categoria, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnio() {
        return anio;
    }
}
