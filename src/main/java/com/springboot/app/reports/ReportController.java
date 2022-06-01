package com.springboot.app.reports;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.business.passenger.PassengerService;
import com.springboot.app.business.passenger.model.PassengerTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/document")
public class ReportController {

	@Autowired
	private PassengerService service;

	@GetMapping()
	public void getDocument(HttpServletResponse response) throws IOException, JRException {

		InputStream stream = this.getClass().getResourceAsStream("/passenger.jrxml");
		JasperReport report = JasperCompileManager.compileReport(stream);

		List<PassengerTO> passengers = this.service.getAll();
		System.out.println(passengers);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(passengers);
		Map<String, Object> parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
	}
}