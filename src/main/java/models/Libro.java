package models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Libro {
    private int idLibro;
    private String titulo;
    private Integer anioPublicado;
    private String genero;
    private String editorial;
    private Autor autorByIdAutor;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_libro", nullable = false)
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 100)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "anio_publicado", nullable = true)
    public Integer getAnioPublicado() {
        return anioPublicado;
    }

    public void setAnioPublicado(Integer anioPublicado) {
        this.anioPublicado = anioPublicado;
    }

    @Basic
    @Column(name = "genero", nullable = true, length = 50)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "editorial", nullable = true, length = 50)
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro && Objects.equals(titulo, libro.titulo) && Objects.equals(anioPublicado, libro.anioPublicado) && Objects.equals(genero, libro.genero) && Objects.equals(editorial, libro.editorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, titulo, anioPublicado, genero, editorial, autorByIdAutor);
    }

    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")
    public Autor getAutorByIdAutor() {
        return autorByIdAutor;
    }

    public void setAutorByIdAutor(Autor autorByIdAutor) {
        this.autorByIdAutor = autorByIdAutor;
    }

    @Override
    public String toString() {
        return "Libro: " + this.titulo + " Año de publicación: " + this.anioPublicado;
    }
}
