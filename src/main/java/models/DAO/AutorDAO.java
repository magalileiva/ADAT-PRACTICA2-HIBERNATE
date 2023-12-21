package models.DAO;

import com.utad.HibernateUtil;
import models.Autor;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

import java.util.Date;
import java.util.List;

public class AutorDAO {
    private Session session;

    public AutorDAO() {
        this.session = HibernateUtil.getSession();
    }
    public Autor crearAutor(String nombre, Date fechaNacimiento, String pais) throws ConstraintViolationException {

        session.beginTransaction();
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setPais(pais);
        autor.setFechaNacimiento(fechaNacimiento);
        //guardo el autor en la bbdd con persist
        session.persist(autor);
        //Confirma la transacción en la base de datos
        session.getTransaction().commit();
        return autor;
    }

    public void borrarAutor(Autor autor){
        session.beginTransaction();
        session.remove(autor);
        session.getTransaction().commit();
    }

    public Autor actualizarAutor(Integer idAutor, String nombre, String pais, Date fechaNacimiento){
        session.beginTransaction();
        Autor autorUpdate = session.get(Autor.class,idAutor);
        if(autorUpdate != null){
            autorUpdate.setNombre(nombre);
            autorUpdate.setPais(pais);
            autorUpdate.setFechaNacimiento(fechaNacimiento);
            session.merge(autorUpdate);
            session.getTransaction().commit();
        }else{
            System.out.println("No se ha podido actualizar el Autor.");
        }
        return autorUpdate;
    }

    public List<Autor> obtenerAutores(){
        return session.createQuery("FROM Autor ", Autor.class).list();
    }

    public Autor obtenerAutorPorId(Integer idAutor){
        return session.get(Autor.class,idAutor);
    }

    //Creamos método que devuelve una lista de objetos Autor, que sea del rango de publicación indicado en el parametro
    public List<Autor> autoresAnioPublicacion(Integer anioMin, Integer anioMax){

        return session.createQuery("FROM Autor a JOIN a.libros l WHERE l.anioPublicado BETWEEN :init AND :final", Autor.class).setParameter("init", anioMin).setParameter("final", anioMax).list();
    }

    public List<Autor> autoresPorNombre(String nombre){

        return session.createQuery("FROM Autor  WHERE nombre LIKE :nombre ", Autor.class).setParameter("nombre", "%"+nombre+"%").list();
    }

}
