package com.utad;

import models.Autor;
import models.DAO.AutorDAO;
import models.DAO.LibroDAO;
import models.Libro;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        AutorDAO autorDAO = new AutorDAO();
        LibroDAO libroDAO = new LibroDAO();
        //Borrar datos de tablas Autor y Libros si tuvieran datos
        for(Libro l :libroDAO.obtenerLibros()){
            libroDAO.borrarLibro(l);
        }
        for(Autor a : autorDAO.obtenerAutores()){
            autorDAO.borrarAutor(a);
        }
        //PRuebas funciones del CRUD: crear, leer, actualizar y eliminar
        //Creamos primer autor
        Autor autor = null;
        try {
            autor = autorDAO.crearAutor("JKR", new Date(65, 7, 31), "Inglaterra");
        } catch (ConstraintViolationException e) {
            System.out.println("Error al crear autor nombre duplicado");
        }
        //Creamos segundo autor
        Autor autor2 = null;
        try {
            autor2 = autorDAO.crearAutor("Isaac Asimov", new Date(65, 7, 31), "Inglaterra");
        } catch (ConstraintViolationException e) {
            System.out.println("Error al crear autor nombre duplicado");
        }
        //Creamos libros para el primer autor
        libroDAO.crearLibro("Harry Potter 1",1995,"Novela","Bloomsbury",autor);
        libroDAO.crearLibro("Harry Potter 2",1998,"Novela","Bloomsbury",autor);
        //Creamos libros para el segundo autor
        Libro libroFundacion = libroDAO.crearLibro("Fundación",1996,"Novela","SantaAna",autor2);
        //Imprimir 1er autor y sus libros
        System.out.println(autor);
        System.out.println(libroDAO.librosDeAutor(autor.getIdAutor()));
        //Imprimir 2do autor y sus libros
        System.out.println(autor2);
        System.out.println(libroDAO.librosDeAutor(autor2.getIdAutor()));

        //Busquedas
        System.out.println("Autor por año de publicación 1997-1998");
        System.out.println(autorDAO.autoresAnioPublicacion(1997,1998));

        System.out.println("Autor por año de publicación 1994-1996");
        System.out.println(autorDAO.autoresAnioPublicacion(1994,1996));

        autor = autorDAO.actualizarAutor(autor.getIdAutor(),"J.K Rowling", autor.getPais(), autor.getFechaNacimiento());
        System.out.println("Autor actualizado");
        System.out.println(autor);
        
        List<Autor> autores  = autorDAO.autoresPorNombre("Asimov");
        for (Autor a:autores){
            System.out.println("Los autores encontrados por el nombre indicado son: "+a);
        }

        libroFundacion = libroDAO.actualizarLibro(libroFundacion.getIdLibro(), libroFundacion.getTitulo(),1978,libroFundacion.getGenero(), libroFundacion.getEditorial());
        System.out.println("Libro actualizado");
        System.out.println(libroFundacion);



        System.out.println(autorDAO.obtenerAutorPorId(1));

        try {
            Autor autor3 = autorDAO.crearAutor("Isaac Asimov", new Date(65, 7, 31), "kjk");
        } catch (ConstraintViolationException e) {
            System.out.println("Error al crear autor nombre duplicado");
        }

    }
}
