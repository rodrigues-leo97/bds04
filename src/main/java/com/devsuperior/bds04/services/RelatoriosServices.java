package com.devsuperior.bds04.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatoriosServices {
	
	
	@Autowired
	UserRepository userRepository;
	

	
	public byte[] gerarRelatorio() throws Exception {
		//URL resource = getClass().getResource("C:\\Users\\leona\\JaspersoftWorkspace\\relatorio-teste-curso-apirest\\Blank_A4.jasper");
		String caminhoJasper = "C:\\Users\\leona\\JaspersoftWorkspace\\relatorio-teste-curso-apirest2\\Blank_A4.jasper";
		
		List<User> usuarios = userRepository.findAll();
		
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(""));
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("EMAIL2", usuarios.get(0).getEmail());
		parametros.put("EMAIL3", usuarios.get(1).getEmail());
		parametros.put("EMAIL", usuarios.get(0).getEmail());
		
		JasperPrint print = JasperFillManager.fillReport(caminhoJasper, parametros, dataSource);
		

		
		return JasperExportManager.exportReportToPdf(print);
		
	}

}
