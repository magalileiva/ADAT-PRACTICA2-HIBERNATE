package models.DAO;

import com.utad.HibernateUtil;
import models.Autor;
import models.Libro;
import org.hibernate.Session;

import java.util.List;

public class LibroDAO {

    private Session session;

    public LibroDAO() {
        this.session = HibernateUtil.getSession();
    }

    public Libro crearLibro(String titulo, int anioPubli, String genero, String editorial, Autor autor) {
        session.beginTransaction();
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnioPublicado(Integer.valueOf(anioPubli));
        libro.setGenero(genero);
        libro.setEditorial(editorial);
        libro.setAutorByIdAutor(autor);
        session.persist(libro);
        session.getTransaction().commit();
        return libro;
    }

    public void borrarLibro(Libro libro){
        session.beginTransaction();
        session.remove(libro);
        session.getTransaction().commit();
    }

    public Libro actualizarLibro(Integer idLibro, String titulo, Integer anio, String genero, String editorial){
        session.beginTransaction();
        Libro libroUpdate = session.get(Libro.class, idLibro);
        if(libroUpdate != null){
            libroUpdate.setTitulo(titulo);
            libroUpdate.setAnioPublicado(Integer.valueOf(anio));
            libroUpdate.setGenero(genero);
            libroUpdate.setEditorial(editorial);
            session.merge(libroUpdate);
            session.getTransaction().commit();
        }
        return libroUpdate;
    }

    //método que devuelve una lista de objetos del tipo Libro.
    public List<Libro> obtenerLibros(){
        // creamos la consulta que solicita todos los objetos de la clase Libro en la base de datos

        return session.createQuery("FROM Libro ", Libro.class).list();
    }

    //método que devuelve un objeto de tipo Libro, tomando por parámetro el Id del libro.
    public Libro obtenerLibroPorId(Integer idLibro){
        // creamos la consulta con session.get, método utilizado para recuperar un objeto específico de la base de datos
        return session.get(Libro.class,idLibro);
    }

    //Creamos la consulta para obtener todos los libros de un autor específico
    public List<Libro> librosDeAutor(int idAutor){
        // creamos la consulta que solicita los objetos de cla clase Libro que tengan el Id igual al pasado por parámetro
        return session.createQuery("FROM Libro WHERE autorByIdAutor.idAutor = :id", Libro.class).setParameter("id",idAutor).list();
    }


}
