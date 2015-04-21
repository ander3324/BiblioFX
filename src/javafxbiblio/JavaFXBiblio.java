/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxbiblio;

import Logica.Libro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author ander
 */
public class JavaFXBiblio extends Application {
        
    //Instancia de Stage Actual:
    static Stage ventanaActual;

    //Instancia actual de objeto LIBRO:
    private static Libro libroActual;
    
    /**
     * @return the libroActual
     */
    public static Libro getLibroActual() {
        return libroActual;
    }

    /**
     * @param aLibroActual the libroActual to set
     */
    public static void setLibroActual(Libro aLibroActual) {
        libroActual = aLibroActual;
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("formLibros.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Gestión de Libros");
        //stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void mostrarMensaje(String mensaje) {
        //Mostrar diálogo:
        Stage dialogStage = new Stage(StageStyle.UTILITY);
        Scene dialog = new Scene(new Group(new Text(40, 120, mensaje)));

        dialogStage.setScene(dialog);
        dialogStage.show();
    }
    
    //Método que crea las ventanas FXML:
    public static void crearVentanas(String recursoFXML, String titulo){
        try{ 
            FXMLLoader loader = new FXMLLoader(JavaFXBiblio.class.getResource(recursoFXML));
            AnchorPane usuCrud = (AnchorPane) loader.load();
             Stage ventana = new Stage();
            ventana.setTitle(titulo);
          
            Scene scene = new Scene(usuCrud);
            ventana.setScene(scene);
           

            ventana.initModality(Modality.APPLICATION_MODAL);
            
            ventanaActual = ventana;
                        

            ventana.showAndWait();  //Comportamiento de los winforms...   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void cerrarVentanas(){
        ventanaActual.close();
    }
    
}
