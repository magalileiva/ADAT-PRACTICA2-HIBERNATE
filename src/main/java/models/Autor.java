package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Autor {
    private int idAutor;
    private String nombre;
    private Date fechaNacimiento;
    private String pais;
    private Set<Libro> libros;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_autor", nullable = false)
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "fecha_nacimiento", nullable = true)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Basic
    @Column(name = "pais", nullable = true, length = 50)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    @OneToMany(mappedBy="autorByIdAutor")
    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return idAutor == autor.idAutor && Objects.equals(nombre, autor.nombre) && Objects.equals(fechaNacimiento, autor.fechaNacimiento) && Objects.equals(pais, autor.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutor, nombre, fechaNacimiento, pais);
    }

    @Override
    public String toString() {
        return "Autor: " + this.nombre + " País: " + this.pais;
    }
}
