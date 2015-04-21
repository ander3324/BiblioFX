/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerTec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class Conexion {

    //Atributos de conexión con BD:
    private static final String USU = "root";
    private static final String PASS = "wacaballa";
    private static final String CC = "jdbc:mysql://localhost:3306/BiblioDB";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conexion = null;

    static ResultSet datos = null;

    //Métodos:
    public static Connection conectar() {
        try {
            Class.forName(DRIVER); //Devolver una instancia del driver MYSQL...
            conexion = null;
            conexion = DriverManager.getConnection(CC, USU, PASS);

            System.out.println("Conexión exitosa...");

            return conexion;

        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static void desconectar() {
        try {
            conexion.close();
            System.out.println("Desconectado...");
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void ejecutarSQL(String comando) {

        try {
            PreparedStatement st = conexion.prepareStatement(comando);
            st.execute(comando);
            datos = st.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
    //Por implementar...
    public static void ejecutarCRUD(String comando){
        try {
            PreparedStatement st = conexion.prepareStatement(comando);
            st.execute(comando);
            //st.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static ResultSet getDatos(){
        return datos;
    }

}