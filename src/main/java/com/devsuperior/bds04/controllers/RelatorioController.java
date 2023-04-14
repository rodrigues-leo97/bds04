package com.devsuperior.bds04.controllers;


import com.devsuperior.bds04.services.RelatoriosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioController {

    @Autowired
    private RelatoriosServices relatoriosServices;

    @GetMapping
    public ResponseEntity<byte[]> gerarRelatorio() throws Exception {
    	byte[] bytesPdf = relatoriosServices.gerarRelatorio();
    	var headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cedula.pdf");
    	return ResponseEntity.ok()
    			.contentType(MediaType.APPLICATION_PDF)
    			.headers(headers)
    			.body(bytesPdf);

    }
    
}
