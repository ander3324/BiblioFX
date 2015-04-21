/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbiblio;

import Logica.Libro;
import Logica.ServiciosLibros;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static javafxbiblio.JavaFXBiblio.mostrarMensaje;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class FormAbmFXMLController implements Initializable {

    @FXML
    private AnchorPane formAbm;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private Button btnAceptar;

    Libro libro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (JavaFXBiblio.getLibroActual() != null) {
            txtIsbn.setText(String.valueOf(JavaFXBiblio.getLibroActual().getIsbn()));
            txtTitulo.setText(JavaFXBiblio.getLibroActual().getTitulo());
            txtAutor.setText(JavaFXBiblio.getLibroActual().getAutor());

            String fecha = String.valueOf(JavaFXBiblio.getLibroActual().getFecha_publicacion());

            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(4, 5));
            int año = Integer.parseInt(fecha.substring(6, 10));
            
            dtpFecha.setValue(LocalDate.of(año, mes, dia));
            //dtpFecha.setValue(LocalDate.parse(fecha, DateTimeFormatter.ISO_DATE));

            JavaFXBiblio.setLibroActual(null);  //Borrar libro

//            int dia, mes, año;
//            dia = JavaFXBiblio.getLibroActual().getFecha_publicacion().getDay();
//            mes = JavaFXBiblio.getLibroActual().getFecha_publicacion().getMonth();
//            año = JavaFXBiblio.getLibroActual().getFecha_publicacion().getYear();
            //dtpFecha.setValue(LocalDate.parse("2014-05-12", DateTimeFormatter.ISO_DATE));       
        }
    }

    @FXML
    private void CerrarAction(ActionEvent event) {
        JavaFXBiblio.cerrarVentanas();
    }

    @FXML
    private void AceptarAction(ActionEvent event) {

        //Crear la nueva instancia de libro...
        libro = new Libro();
        libro.setIsbn(Integer.parseInt(txtIsbn.getText()));
        libro.setTitulo(txtTitulo.getText());
        libro.setAutor(txtAutor.getText());
        libro.setFecha_publicacion(String.valueOf(dtpFecha.getValue()));

        //Verificar si es alta o modificación...
        if (JavaFXBiblio.ventanaActual.getTitle().startsWith("Nuevo")) {

            ServiciosLibros.insertLibro(libro);
            mostrarMensaje("Nuevo libro añadido.");
        } else {
            ServiciosLibros.modificarLibro(libro);
            mostrarMensaje("Libro modificado.");
        }

        JavaFXBiblio.cerrarVentanas();
    }

}
