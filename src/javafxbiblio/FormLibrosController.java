/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbiblio;

import Logica.Libro;
import Logica.ServiciosLibros;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import static javafxbiblio.JavaFXBiblio.mostrarMensaje;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class FormLibrosController implements Initializable {

    @FXML
    private Pane frmLibros;
    @FXML
    private TableView<Libro> tbvLibros;
    @FXML
    private Button btnBorrar;
    
    @FXML
    private TextField txtTitulo;
    
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private Button btnImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarLibros();
    }

    //Método que recibe la colección que se cargará en el TableView:
    private void llenarTabla(List<Libro> libros) {
        
        //Obtener los campos del tableview:
        ObservableList<Libro> datos = tbvLibros.getItems();
        datos.clear();  //limpiar cualquier carga previa...

        //Equivalente a FOREACH:
        //Recorrer la colección enviada y
        //añadirla al tableview:
        for (Libro l : libros) {
            datos.add(l);
        }
    }

    private void cargarLibros() {
        
        llenarTabla(ServiciosLibros.selectLibros());

//        ObservableList<Libro> datos = tbvLibros.getItems();
//
//        datos.clear();
//
//        List<Libro> libs = ServiciosLibros.selectLibros();
//
//        //Equivalente a FOREACH:
//        for (Libro l : libs) {
//            datos.add(l);
//        }

    }

    @FXML
    private void NuevoAction(ActionEvent event) {
        JavaFXBiblio.crearVentanas("formAbmFXML.fxml", "Nuevo Libro");

        cargarLibros();
    }

    @FXML
    private void ModificarAction(ActionEvent event) {

        try {

            //Alternativa: tbvLibros.getFocusModel().getFocusedItem().getTitulo()
            Libro l = new Libro();
            l.setIsbn(tbvLibros.getSelectionModel().getSelectedItem().getIsbn());
            l.setTitulo(tbvLibros.getSelectionModel().getSelectedItem().getTitulo());
            l.setAutor(tbvLibros.getSelectionModel().getSelectedItem().getAutor());
            String fecha = tbvLibros.getSelectionModel().getSelectedItem().getFecha_publicacion().substring(6, 9) + "-"
                    + tbvLibros.getSelectionModel().getSelectedItem().getFecha_publicacion().substring(3, 4) + "-"
                    + tbvLibros.getSelectionModel().getSelectedItem().getFecha_publicacion().substring(0, 1);

            //l.setFecha_publicacion("2012-05-01");
            l.setFecha_publicacion(tbvLibros.getSelectionModel().getSelectedItem().getFecha_publicacion());

            //Enviar el LIBRO al controlador:       
            JavaFXBiblio.setLibroActual(l);

            //Abrir ventana de modificar:
            JavaFXBiblio.crearVentanas("formAbmFXML.fxml", "Modificar Libro");

            cargarLibros();
        } catch (Exception e) {
            mostrarMensaje("No hay libros seleccionados para modificar.");
        }
    }

    @FXML
    private void BorrarAction(ActionEvent event) {
        ServiciosLibros.deleteLibro(tbvLibros.getSelectionModel().getSelectedItem().getIsbn());
        //mostrarMensaje("Libro eliminado.");
        cargarLibros();
    }

    @FXML
    private void BuscarPorTitulo(KeyEvent event) {
        llenarTabla(ServiciosLibros.selectLibros(txtTitulo.getText().trim())); 
    }

    @FXML
   private void BuscarPorFecha(){
       llenarTabla(ServiciosLibros.selectLibrosPorFecha(String.valueOf(dtpFecha.getValue())));
   }

    @FXML
    private void ImprimirAction(ActionEvent event) {
        //Lo que faltaría para largarme a JAVA...
    }
}
