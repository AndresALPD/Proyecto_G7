package com.Proyecto.service.impl;

import org.springframework.stereotype.Service;
import com.Proyecto.service.ReporteService;
import java.io.*;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public ResponseEntity<Resource> generaReporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo) throws IOException {

        try {
            String estilo = tipo.equalsIgnoreCase("vPdf")
                    ? "inline;" : "attachment;";

            //Se establece la ruta de los reportes
            String reportePath = "reportes";

            //Se define la salida temporal del reporte generado
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            //Se establece la funte para leer el reporte .jasper
            ClassPathResource fuente = new ClassPathResource(
                    reportePath
                    + File.separator
                    + reporte
                    + ".jasper"
            );

            //Se define el objeto quee lee el archivo de reportes .jasper
            InputStream elReporte = fuente.getInputStream();

            //Se genra el reporte en memoria
            var reporteJasper = JasperFillManager.fillReport(
                    elReporte, parametros, dataSource.getConnection());

            //Se genera respuesta al ususario a partir de aca
            MediaType mediaType = null;
            String archivoSalida = "";

            //Se debe decidir cual tipo de reporte se genera
            switch (tipo) {

                case "Pdf", "vPdf" -> {//Se generará un reporte en PDF
                    JasperExportManager
                            .exportReportToPdfStream(
                                    reporteJasper,
                                    salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                case "Xls" -> {//Se descargará un Excel
                    JRXlsxExporter paraExcel = new JRXlsxExporter();
                    paraExcel.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    paraExcel.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(
                                    salida));
                    SimpleXlsxReportConfiguration configuracion
                            = new SimpleXlsxReportConfiguration();
                    configuracion.setDetectCellType(true);
                    configuracion.setCollapseRowSpan(true);

                    paraExcel.setConfiguration(configuracion);
                    paraExcel.exportReport();

                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                case "Csv" -> {//Se descargará un texto tipo CSV
                    JRCsvExporter paraCsv = new JRCsvExporter();
                    paraCsv.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    paraCsv.setExporterOutput(
                            new SimpleWriterExporterOutput(
                                    salida));

                    paraCsv.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                }

            }
            //A partir de aca se realiza la repuesta al ususario
            byte[] data = salida.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition",
                    estilo + "filename=\"" + archivoSalida + "\"");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(
                            new InputStreamResource(
                                    new ByteArrayInputStream(data)
                            ));
        } catch (JRException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}