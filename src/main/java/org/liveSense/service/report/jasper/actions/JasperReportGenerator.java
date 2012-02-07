package org.liveSense.service.report.jasper.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JasperReportGenerator {
	public static Logger log = LoggerFactory.getLogger(JasperReportGenerator.class);
	
	public static void doAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			

			String	INPUT_XML = req.getParameter("INPUT_XML" );
			String	JRXML_FILE = req.getParameter("JRXML_FILE" );
			String	JASPER_FILE = req.getParameter("JASPER_FILE" );
			String	PARENT_XPATH = req.getParameter("PARENT_XPATH" );
			String	EXPORT_TYPE = req.getParameter("EXPORT_TYPE" );
			String	OUTPUT_FILENAME = req.getParameter("OUTPUT_FILENAME" );
				
			HashMap<String, Object> hm = new HashMap<String, Object>();

			JasperReport jasperReport = null;
			JasperPrint jp;

				
			//jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File(INPUT_XML)));
			
			/*
			try {
				JasperDesign jasperDesign =	JRXmlLoader.load(new FileInputStream(new File(JRXML_FILE)));
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				JRSaver.saveObject(jasperReport, new FileOutputStream(new File(JASPER_FILE)));
				//JasperCompileManager.compileReportToStream(new FileInputStream(new File(INPUT_XML)), new FileOutputStream(new File(JASPER_FILE)));
			} catch (Exception e) {
				log.error("Error compiling", e);
			}
			//jasperReport = (JasperReport) JRLoader.loadObject(new File(JASPER_FILE));
			*/
			jasperReport = (JasperReport) JRLoader.loadObject(new FileInputStream(new File(JASPER_FILE)));
			
			jp = JasperFillManager.fillReport(jasperReport, hm, new JRXmlDataSource(INPUT_XML,PARENT_XPATH));				

				if ("csv".equalsIgnoreCase(EXPORT_TYPE)){
					JRCsvExporter exporter = new JRCsvExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, OUTPUT_FILENAME);
					exporter.exportReport();
					
				}


				else if ("pdf".equalsIgnoreCase(EXPORT_TYPE)) {
					if (OUTPUT_FILENAME != null){
						JasperExportManager.exportReportToPdfFile(jp, OUTPUT_FILENAME);
						resp.getWriter().println("<html><body>generated report to "+OUTPUT_FILENAME+"</body></html>");
					}
					else {
						resp.setContentType("application/octet-stream");
						resp.setHeader("Content-disposition","attachment; filename=\"report.pdf\"");						
						JasperExportManager.exportReportToPdfStream(jp, resp.getOutputStream());
						resp.getOutputStream().flush();
					}
				}
				else if ("html".equalsIgnoreCase(EXPORT_TYPE) ) {
					JRHtmlExporter exporter = new JRHtmlExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, EXPORT_TYPE);
					exporter.exportReport();
				}
				else if ("rtf".equalsIgnoreCase(EXPORT_TYPE)) {
					JRRtfExporter exporter = new JRRtfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, OUTPUT_FILENAME);
					exporter.exportReport();
				}
				
			} catch (Exception e) {
				log.error("Error generating report", e);
			}
		
	}
}
