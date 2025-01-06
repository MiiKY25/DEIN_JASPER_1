package org.mikel.dein_jasper_1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.mikel.dein_jasper_1.bbdd.ConexionBBDD;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal para ejecutar un informe JasperReports.
 */
public class LanzadorJasper extends Application {


    /**
     * Metodo principal de ejecución de la aplicación. Llama al metodo {@link #start(Stage)}
     * para inicializar y mostrar el informe basado en JasperReports.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args); // Inicia la aplicación JavaFX
    }


    /**
     * Metodo encargado de la lógica principal para generar y mostrar el informe.
     * Este metodo establece la conexión a la base de datos, carga el informe JasperReport,
     * rellena el informe con datos de la base de datos y finalmente lo muestra usando un visor de informes.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        ConexionBBDD db;
        try {
            db = new ConexionBBDD();
            // Carga el archivo Jasper del informe
            InputStream reportStream = db.getClass().getResourceAsStream("/jasper/Ejercicio1.jasper");

//           Comprobar si existe el archivo
//            if (reportStream != null) {
//                System.out.println("archivo encontrado");
//                return;
//            } else {
//                System.out.println("archivo NO encontrado");
//            }

            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, db.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);

        } catch (SQLException e) {
            mostrarError("No se ha podido establecer conexion con la Base de Datos");
            Platform.exit();
        } catch (JRException e) {
            e.printStackTrace();
            mostrarError("Error al generar el informe");
            Platform.exit();
        }finally {
            Platform.exit();
        }
    }

    /**
     * Metodo que muestra un mensaje de error en una ventana emergente.
     *
     * @param error El mensaje de error que se mostrará en la ventana emergente.
     */
    void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
