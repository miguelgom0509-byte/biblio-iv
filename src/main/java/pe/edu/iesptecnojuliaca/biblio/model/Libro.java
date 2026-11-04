package pe.edu.iesptecnojuliaca.biblio.model;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private int stock; // El atributo que falta

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, String categoria, int anio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    // ¡ESTO ES LO QUE HACE QUE POSTMAN MUESTRE EL STOCK!
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}