package com.Proyecto.service;

import java.util.Map;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;


 public interface ReporteService {

     //Se define la firma del metodo que genera los reportes con los siguientes parametros
     // 1. Reporte: es el nomnre del archivo reportes (.jasper)
     // 2. parametros: un Map que contiene los parametros del reporte si los ocupa
     // 3. tipo: es el tipo de report: vPdf, Pdf, Xls, Cvs

     public ResponseEntity<Resource> generaReporte(
              String reporte, 
              Map<String, Object> parametros,
              String tipo)throws IOException;

}