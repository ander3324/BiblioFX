/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import SerTec.Conexion;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class ServiciosLibros {
    
    private static Libro lib;
    private static ArrayList<Libro> libs = new ArrayList<Libro>();

    private static void cargarListaLibros() {
        try {
            lib = new Libro();
            lib.setIsbn(Integer.parseInt(Conexion.getDatos().getString(1)));
            lib.setTitulo(Conexion.getDatos().getString(2));
            lib.setAutor(Conexion.getDatos().getString(3));
            lib.setFecha_publicacion(Conexion.getDatos().getString(4));
            
            //lib.setFecha_publicacion(Date.valueOf(Conexion.getDatos().getString(4)));
             //lib.setFecha_publicacion(Date.valueOf(Conexion.getDatos().getString(4).substring(0, 10)).toLocalDate().format(DateTimeFormatter.ofPattern("dd/mm/yyyy")));
           
            libs.add(lib);
            
            System.out.println(lib.getTitulo());

        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static List<Libro> selectLibros() {

        try {
            libs.clear();
            Conexion.conectar();
            Conexion.ejecutarSQL("Select ISBN, TIT, AUT, Date_format(FEC,'%d/%m/%Y') From LIBROS");

            while (Conexion.getDatos().next()) {
                cargarListaLibros();
                
            }
            
            Conexion.desconectar();
            
              
            
            return libs;

        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static List<Libro> selectLibros(String titulo) {

        try {
            libs.clear();
            Conexion.conectar();
            Conexion.ejecutarSQL("Select ISBN, TIT, AUT, Date_format(FEC,'%d/%m/%Y') From LIBROS " +
                    "Where TIT Like '%" + titulo + "%'");

            while (Conexion.getDatos().next()) {
                cargarListaLibros();
            }
            
            Conexion.desconectar();
               
            return libs;

        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static List<Libro> selectLibrosPorFecha(String fecha) {

        try {
            libs.clear();
            Conexion.conectar();
            Conexion.ejecutarSQL("Select ISBN, TIT, AUT, Date_format(FEC,'%d/%m/%Y') From LIBROS " +
                    "Where FEC Like '" + fecha + "'");

            while (Conexion.getDatos().next()) {
                cargarListaLibros();
            }
            
            Conexion.desconectar();
               
            return libs;

        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
//    public static List<Categoria> selectCategorias(String categoria) {
//
//        try {
//            cats.clear();
//            Conexion.conectar();
//            Conexion.ejecutarSQL("Select * From CATEGORIAS Where CAT Like '" + categoria + "%'");
//
//            while (Conexion.getDatos().next()) {
//                cargarListaCategorias();
//            }
//            
//            Conexion.desconectar();
//            
//            return cats;
//
//        } catch (Exception e) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
//            return null;
//        }
//    }
//    
//    //Métodos CRUD:
    public static void insertLibro(Libro l) {

        try {
            
            Conexion.conectar();
            Conexion.ejecutarCRUD("Insert Into LIBROS (ISBN, TIT, AUT, FEC) Values(" + 
                    l.getIsbn() + ", '" +
                    l.getTitulo() + "', '" +
                    l.getAutor() + "', '" + 
                    l.getFecha_publicacion() + 
                    "')");
            
            Conexion.desconectar();
            
            System.out.println("Libro añadido...");
            
        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void modificarLibro(Libro l) {

//        Date_format(GASTOS.FEC,'%d/%m/%Y')Date_format(GASTOS.FEC,'%d/%m/%Y')
        try {
            
            Conexion.conectar();
            Conexion.ejecutarCRUD("Update LIBROS " + 
                    " Set ISBN = " + l.getIsbn() + 
                    ", TIT = '" + l.getTitulo() + 
                    "', AUT = '" + l.getAutor() + 
                    "', FEC = '" + l.getFecha_publicacion() + 
                    "' Where ISBN = " + l.getIsbn());
            
            Conexion.desconectar();
            
            System.out.println("Libro modificado...");
            
        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static void deleteLibro(int libID){
        Conexion.conectar();

            Conexion.ejecutarCRUD("Delete From LIBROS Where ISBN = " + libID);

            Conexion.desconectar();
            System.out.println("Libro borrado...");
    }
    
}
