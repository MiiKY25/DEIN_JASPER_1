package org.mikel.dein_jasper_1;

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
public class LanzadorJasper {

    /**
     * Metodo principal que lanza el informe JasperReports.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
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
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
